package com.cairns.lambda;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link UncaughtCheckedException}.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
public class TestUncaughtCheckedException {
  private static final Class<?> classToTest = UncaughtCheckedException.class;
  private static final Class<?>[] expectedCtorParamTypes = { Exception.class };

  /**
   * This test ensures that the exception type is publicly visible, not extendable and is a {@link RuntimeException}.
   */
  @Test
  public void testClassAttributes() {
    int modifiers = classToTest.getModifiers();
    Assert.assertTrue(Modifier.isPublic(modifiers));
    Assert.assertTrue(Modifier.isFinal(modifiers));
    Assert.assertEquals(RuntimeException.class, classToTest.getSuperclass());
  }

  /**
   * This test ensures the only constructor is package-private and takes a single exception param.
   */
  @Test
  public void testConstructor() {
    Constructor<?>[] ctors = classToTest.getDeclaredConstructors();
    Assert.assertEquals(1, ctors.length);
    Constructor<?> ctor = ctors[0];
    int modifiers = ctor.getModifiers();
    Assert.assertFalse(Modifier.isPublic(modifiers));
    Assert.assertFalse(Modifier.isPrivate(modifiers));
    Assert.assertFalse(Modifier.isProtected(modifiers));
    Assert.assertArrayEquals(expectedCtorParamTypes, ctor.getParameterTypes());
  }
}
