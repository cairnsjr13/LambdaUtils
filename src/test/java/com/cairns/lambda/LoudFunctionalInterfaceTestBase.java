package com.cairns.lambda;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Base class for tests of loud extendsions of {@link FunctionalInterface}s.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
abstract class LoudFunctionalInterfaceTestBase {
  private static final int OF_METHOD = 0;
  private static final int QUIET_METHOD = 1;
  private static final int LOUD_METHOD = 2;
  private static final Class<?>[] expectedLoudExceptionTypes = { Exception.class };

  private final Class<?> classToTest;
  private final Method[] methods;

  // Impl note: we sort the methods array by length of name, this ensures of will be first, quiet second, and loud last.
  protected LoudFunctionalInterfaceTestBase(Class<?> classToTest) {
    this.classToTest = classToTest;
    this.methods = classToTest.getMethods();
    Comparator<Method> lengthCmp = Comparator.comparing((m) -> m.getName().length());
    Arrays.sort(methods, lengthCmp.thenComparing(Method::getName));
  }

  /**
   * This test ensures the class being tested has the proper structure and conventions, namely:
   *   - is an interface that is annotated as a {@link FunctionalInterface}
   *   - has 3 methods
   *   - extends a {@link FunctionalInterface}
   *   - is named by prefixing "Loud" to the base interface's name
   *   - provides a default implementation for that base interface's method
   */
  @Test
  public void testInheritanceAndStructure() throws ReflectiveOperationException {
    Assert.assertTrue(classToTest.isInterface());
    Assert.assertNotNull(classToTest.getAnnotation(FunctionalInterface.class));
    Assert.assertEquals(3, methods.length);
    Class<?>[] interfaces = classToTest.getInterfaces();
    Assert.assertEquals(1, interfaces.length);
    Class<?> baseInterface = interfaces[0];
    Assert.assertNotNull(baseInterface.getAnnotation(FunctionalInterface.class));
    Assert.assertEquals("Loud" + baseInterface.getSimpleName(), classToTest.getSimpleName());
    Method functionalMethod = Arrays.stream(baseInterface.getMethods())
        .filter((m) -> Modifier.isAbstract(m.getModifiers()))
        .findFirst().get();
    Method defaultedMethod = classToTest.getMethod(functionalMethod.getName(), functionalMethod.getParameterTypes());
    Assert.assertEquals(methods[QUIET_METHOD], defaultedMethod);
    Assert.assertTrue(defaultedMethod.isDefault());
    Assert.assertEquals(functionalMethod.getReturnType(), defaultedMethod.getReturnType());
  }

  /**
   * This test ensures the class provides a "syntactic sugar" static method to easily create a loud instance.
   */
  @Test
  public void testOfMethod() {
    Method ofMethod = methods[OF_METHOD];
    Assert.assertEquals("of", ofMethod.getName());
    Assert.assertTrue(Modifier.isStatic(ofMethod.getModifiers()));
    Assert.assertEquals(1, ofMethod.getParameterCount());
    Assert.assertEquals(classToTest, ofMethod.getParameterTypes()[0]);
    Assert.assertEquals(classToTest, ofMethod.getReturnType());
  }

  /**
   * This test ensures the "quiet" method is indeed defaulted and throws no exceptions.
   */
  @Test
  public void testQuietMethod() {
    Method quietMethod = methods[QUIET_METHOD];
    Assert.assertFalse(Modifier.isStatic(quietMethod.getModifiers()));
    Assert.assertTrue(quietMethod.isDefault());
    Assert.assertEquals(0, quietMethod.getExceptionTypes().length);
  }

  /**
   * This test ensures the "loud" method is:
   *   - named by suffixing the quiet method name with "Loudly"
   *   - is abstract (functional interface)
   *   - has the same param list as the quiet method
   *   - throws {@link Exception}
   */
  @Test
  public void testLoudMethod() {
    Method quietMethod = methods[QUIET_METHOD];
    Method loudMethod = methods[LOUD_METHOD];
    Assert.assertEquals(quietMethod.getName() + "Loudly", loudMethod.getName());
    Assert.assertFalse(Modifier.isStatic(quietMethod.getModifiers()));
    Assert.assertTrue(Modifier.isAbstract(loudMethod.getModifiers()));
    Assert.assertArrayEquals(quietMethod.getParameterTypes(), loudMethod.getParameterTypes());
    Assert.assertArrayEquals(expectedLoudExceptionTypes, loudMethod.getExceptionTypes());
  }
}
