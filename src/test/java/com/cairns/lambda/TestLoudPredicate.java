package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudPredicate}.
 *
 * @author cairnsjr13 - created 14/Sep/2021
 */
public class TestLoudPredicate extends LoudFunctionalInterfaceTestBase<LoudPredicate<String>> {
  public TestLoudPredicate() {
    super(LoudPredicate.class, oArg1);
  }

  @Override
  protected LoudPredicate<String> succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(true);
  }

  @Override
  protected LoudPredicate<String> failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
