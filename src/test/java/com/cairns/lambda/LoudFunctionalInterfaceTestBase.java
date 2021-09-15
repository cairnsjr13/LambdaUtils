package com.cairns.lambda;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;

/**
 * Base class for tests of loud extensions of {@link FunctionalInterface}s.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
abstract class LoudFunctionalInterfaceTestBase {
  private static final Class<?>[] expectedLoudExceptionTypes = { Exception.class };

  private final Class<?> classToTest;

  protected LoudFunctionalInterfaceTestBase(Class<?> classToTest) {
    this.classToTest = classToTest;
  }

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
   * The method should be static, take in its own class type as a param, and return the same type.
   */
  @Test
  public void testOfMethod() {
    Method ofMethod = getOfMethod(classToTest);
    Assert.assertNotNull(ofMethod);
    Assert.assertTrue(Modifier.isStatic(ofMethod.getModifiers()));
    Assert.assertArrayEquals(new Class<?>[] { classToTest }, ofMethod.getParameterTypes());
    Assert.assertEquals(classToTest, ofMethod.getReturnType());
  }

  /**
   * This test ensures the "quiet" method is indeed defaulted and throws no exceptions.
   */
  @Test
  public void testQuietMethod() throws ReflectiveOperationException {
    Method quietMethod = getQuietMethod(classToTest);
    Assert.assertNotNull(quietMethod);
    Assert.assertFalse(Modifier.isStatic(quietMethod.getModifiers()));
    Assert.assertTrue(quietMethod.isDefault());
    Assert.assertEquals(0, quietMethod.getExceptionTypes().length);
  }

  /**
   * This test ensures the "loud" method is:
   *   - not static
   *   - abstract (functional interface)
   *   - has the same return type as the quiet method
   *   - throws {@link Exception}
   */
  @Test
  public void testLoudMethod() throws ReflectiveOperationException {
    Method quietMethod = getQuietMethod(classToTest);
    Method loudMethod = getLoudMethod(classToTest);
    Assert.assertNotNull(loudMethod);
    Assert.assertFalse(Modifier.isStatic(loudMethod.getModifiers()));
    Assert.assertTrue(Modifier.isAbstract(loudMethod.getModifiers()));
    Assert.assertEquals(quietMethod.getReturnType(), loudMethod.getReturnType());
    Assert.assertArrayEquals(expectedLoudExceptionTypes, loudMethod.getExceptionTypes());
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
}
