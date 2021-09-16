package com.cairns.lambda;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;

/**
 * Base class for tests of loud extensions of {@link FunctionalInterface}s.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 *
 * @param <T> the type of loud interface being tested
 */
abstract class LoudFunctionalInterfaceTestBase<T> {
  protected static final double dArg1 = 13.22d;
  protected static final double dArg2 = 15.29d;
  protected static final double dReturn = 20.31d;
  protected static final int iArg1 = 13;
  protected static final int iArg2 = 22;
  protected static final int iReturn = 15;
  protected static final long lArg1 = 29L;
  protected static final long lArg2 = 20L;
  protected static final long lReturn = 31L;
  protected static final String oArg1 = "arg1";
  protected static final String oArg2 = "arg2";
  protected static final String oReturn = "return";

  private static final Class<?>[] expectedLoudExceptionTypes = { Exception.class };
  private static final Map<Class<?>, Class<?>> expectedToActualTypes = new HashMap<>();

  static {
    expectedToActualTypes.put(double.class, Double.class);
    expectedToActualTypes.put(int.class, Integer.class);
    expectedToActualTypes.put(long.class, Long.class);
    expectedToActualTypes.put(Object.class, String.class);  // By convention non primitive generics should use String
  }

  private final Class<?> classToTest;
  private final Object[] argsToPass;

  protected LoudFunctionalInterfaceTestBase(Class<?> classToTest, Object... argsToPass) {
    this.classToTest = classToTest;
    this.argsToPass = argsToPass;
    ensureValidArgsToPass(classToTest, argsToPass);
  }

  /**
   * Test classes should return an instance of the interface being tested that succeeds
   * following the semantics laid out in the {@link Indicator} class.  It is REQUIRED to
   * call the {@link Indicator#registerArgs(Object...)} method even with an empty arg list.
   */
  protected abstract T succeedingInstance(Indicator indicator);

  /**
   * Test classes should return an instance that does nothing but throws an exception from the given supplier.
   */
  protected abstract T failingInstance(Supplier<Exception> toThrow);

  /**
   * This test ensures the class being tested has the proper inheritance structure and conventions, namely:
   *   - is an interface that is annotated as a {@link FunctionalInterface}
   *   - extends exactly one {@link FunctionalInterface}
   *   - is named by prefixing "Loud" to the base interface's name
   */
  @Test
  public void testInheritance() {
    Assert.assertTrue(classToTest.isInterface());
    Assert.assertNotNull(classToTest.getAnnotation(FunctionalInterface.class));
    Class<?> baseInterface = getQuietFunctionalInterface(classToTest);
    Assert.assertNotNull(baseInterface.getAnnotation(FunctionalInterface.class));
    Assert.assertEquals("Loud" + baseInterface.getSimpleName(), classToTest.getSimpleName());
  }

  /**
   * This test ensures the class provides a "syntactic sugar" static method to easily create a loud instance.
   * The method should be public, static, take in its own class type as a param, and return the same type.
   */
  @Test
  public void testOfMethodConventions() {
    Method ofMethod = getOfMethod(classToTest);
    Assert.assertNotNull(ofMethod);
    Assert.assertTrue(Modifier.isPublic(ofMethod.getModifiers()));
    Assert.assertTrue(Modifier.isStatic(ofMethod.getModifiers()));
    Assert.assertArrayEquals(new Class<?>[] { classToTest }, ofMethod.getParameterTypes());
    Assert.assertEquals(classToTest, ofMethod.getReturnType());
  }

  /**
   * This test ensures that the "of" method returns the arg passed in, but does not try to do anything with it.
   */
  @Test
  public void testOfMethodBehavior() throws ReflectiveOperationException {
    Assert.assertNull(getOfMethod(classToTest).invoke(null, new Object[] { null }));

    T instance = succeedingInstance(null);
    Object result = getOfMethod(classToTest).invoke(null, instance);
    Assert.assertSame(instance, result);
  }

  /**
   * This test ensures the "quiet" method is indeed defaulted and throws no exceptions.
   */
  @Test
  public void testQuietMethodConventions() throws ReflectiveOperationException {
    Method quietMethod = getQuietMethod(classToTest);
    Assert.assertNotNull(quietMethod);
    Assert.assertFalse(Modifier.isStatic(quietMethod.getModifiers()));
    Assert.assertTrue(quietMethod.isDefault());
    Assert.assertEquals(0, quietMethod.getExceptionTypes().length);
  }

  /**
   * This test ensures that the quiet method passes args around and returns the correct value on success.
   */
  @Test
  public void testQuietMethodSuccess() throws ReflectiveOperationException {
    runMethodSuccessTest(getQuietMethod(classToTest));
  }

  /**
   * This test ensures that the quiet method only wraps checked exceptions on failure.
   */
  @Test
  public void testQuietMethodThrows() throws ReflectiveOperationException {
    Method quietMethod = getQuietMethod(classToTest);
    runMethodFailureTest(quietMethod, TestingRuntimeException::new, TestingRuntimeException.class);
    runMethodFailureTest(quietMethod, TestingCheckedException::new, UncaughtCheckedException.class);
  }

  /**
   * This test ensures the "loud" method is:
   *   - not static
   *   - abstract (functional interface)
   *   - has the same return type as the quiet method
   *   - throws {@link Exception}
   */
  @Test
  public void testLoudMethodConventions() throws ReflectiveOperationException {
    Method quietMethod = getQuietMethod(classToTest);
    Method loudMethod = getLoudMethod(classToTest);
    Assert.assertNotNull(loudMethod);
    Assert.assertFalse(Modifier.isStatic(loudMethod.getModifiers()));
    Assert.assertTrue(Modifier.isAbstract(loudMethod.getModifiers()));
    Assert.assertEquals(quietMethod.getReturnType(), loudMethod.getReturnType());
    Assert.assertArrayEquals(expectedLoudExceptionTypes, loudMethod.getExceptionTypes());
  }

  /**
   * This test ensures that the loud method passes args around and returns the correct value on success.
   */
  @Test
  public void testLoudMethodSuccess() throws ReflectiveOperationException {
    runMethodSuccessTest(getLoudMethod(classToTest));
  }

  /**
   * This test ensures that the loud method does not wrap checked or unchecked exceptions on failure.
   */
  @Test
  public void testLoudMethodThrows() throws ReflectiveOperationException {
    Method loudMethod = getLoudMethod(classToTest);
    runMethodFailureTest(loudMethod, TestingRuntimeException::new, TestingRuntimeException.class);
    runMethodFailureTest(loudMethod, TestingCheckedException::new, TestingCheckedException.class);
  }

  /**
   * Runs a test of a method that completes successfully.  Ensures the args passed are correct and return matches.
   */
  private void runMethodSuccessTest(Method method) throws ReflectiveOperationException {
    Indicator indicator = new Indicator();
    T instance = succeedingInstance(indicator);
    Object result = method.invoke(instance, argsToPass);
    Assert.assertEquals(indicator.expectedResult, result);
    Assert.assertArrayEquals(argsToPass, indicator.passedArgs);
  }

  /**
   * Runs a test of a method that throws an exception from the given supplier.  Ensures that
   * exception is propagated correctly by matching its type exactly to the given expected type.
   */
  private void runMethodFailureTest(
      Method method,
      Supplier<Exception> exSupplier,
      Class<? extends Exception> expectedThrowType
  ) throws ReflectiveOperationException {
    try {
      T instance = failingInstance(exSupplier);
      method.invoke(instance, argsToPass);
      Assert.fail("Should not have succeeded");
    }
    catch (InvocationTargetException e) {
      Assert.assertEquals(expectedThrowType, e.getCause().getClass());
    }
  }

  /**
   * Ensures that the arguments passed to the constructor match the expected types from the interface.
   */
  private static void ensureValidArgsToPass(Class<?> classToTest, Object[] argsToPass) {
    LoudRunnable.of(() -> {
      Class<?>[] expectedParameterTypes = Arrays.stream(getQuietMethod(classToTest).getParameterTypes())
          .map(expectedToActualTypes::get)
          .toArray(Class<?>[]::new);
      Class<?>[] actualParameterTypes = Arrays.stream(argsToPass).map(Object::getClass).toArray(Class<?>[]::new);
      Assert.assertArrayEquals(expectedParameterTypes, actualParameterTypes);
    }).run();
  }

  /**
   * Retrieves the (expectedly 1) base interface class object of the given test class that lives outside its package.
   */
  private static Class<?> getQuietFunctionalInterface(Class<?> classToTest) {
    Class<?>[] interfaces = classToTest.getInterfaces();
    List<Class<?>> interfacesOutOfPkg = Arrays.stream(interfaces)
        .filter((c) -> !classToTest.getPackage().equals(c.getPackage()))
        .collect(Collectors.toList());
    Assert.assertEquals(1, interfacesOutOfPkg.size());
    return interfacesOutOfPkg.get(0);
  }

  /**
   * Retrieves the of method from the given class we are testing.  Ensures there is only one method method named "of".
   */
  private static Method getOfMethod(Class<?> classToTest) {
    List<Method> ofMethods = Arrays.stream(classToTest.getMethods())
        .filter((m) -> "of".equals(m.getName()))
        .collect(Collectors.toList());
    Assert.assertEquals(1, ofMethods.size());
    return ofMethods.get(0);
  }

  /**
   * Retrieves the method that is being overridden and defaulted by the extension class being tested.
   * Ensures the baseInterface has only 1 abstract method, finds the method in the class being tested
   * that has the same name and parameter types, and returns that method.
   */
  private static Method getQuietMethod(Class<?> classToTest) throws ReflectiveOperationException {
    List<Method> baseFnlMethods = Arrays.stream(getQuietFunctionalInterface(classToTest).getMethods())
        .filter((m) -> Modifier.isAbstract(m.getModifiers()))
        .collect(Collectors.toList());
    Assert.assertEquals(1, baseFnlMethods.size());
    Method baseFnlMethod = baseFnlMethods.get(0);
    return classToTest.getMethod(baseFnlMethod.getName(), baseFnlMethod.getParameterTypes());
  }

  /**
   * Retrieves the abstract method that is identified by suffixing "Loudly" to the quiet method.
   */
  private static Method getLoudMethod(Class<?> classToTest) throws ReflectiveOperationException {
    Method quietMethod = getQuietMethod(classToTest);
    return classToTest.getMethod(quietMethod.getName() + "Loudly", quietMethod.getParameterTypes());
  }

  /**
   * Tracking object used to remember args passed to instances and what the tried to return (if anything).
   * The {@link #registerArgs(Object...)} method should always be called first.  This method uses chaining
   * (by returning itself) to make (optionally) calling {@link #thenReturn(Object)} simpler.  Examples:
   *
   * <pre><code>
   *   return (left, right) -> indicator.registerArgs(left, right);       // for void method
   *
   *   return () -> indicator.registerArgs().thenReturn(13L);             // for returning method
   *
   *   return (arg) -> indicator.registerArgs(arg).thenReturn("return");  // for methods doing both
   * </code></pre>
   *
   * @author cairnsjr13 - created 16/Sep/2021
   */
  protected static final class Indicator {
    private Object[] passedArgs;
    private Object expectedResult;

    /**
     * This method must ALWAYS be called, even if an empty arg list is passed in.
     * This method uses chaining to make {@link #thenReturn(Object)} easier to call.
     */
    protected Indicator registerArgs(Object... passedArgs) {
      this.passedArgs = passedArgs;
      return this;
    }

    /**
     * This method is optional and does not need to be called for void methods.
     * {@link #registerArgs(Object...)} MUST be called first.
     * The passed value will be returned to make callsites less verbose by allowing inlined lambdas.
     */
    protected <T> T thenReturn(T expectedResult) {
      Assert.assertNotNull(passedArgs);
      this.expectedResult = expectedResult;
      return expectedResult;
    }
  }

  /**
   * Runtime exception that can be used in tests.
   *
   * @author cairnsjr13 - created 16/Sep/2021
   */
  private static final class TestingRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;
  }

  /**
   * Checked exception that can be used in tests.
   *
   * @author cairnsjr13 - created 16/Sep/2021
   */
  private static final class TestingCheckedException extends Exception {
    private static final long serialVersionUID = 1L;
  }
}
