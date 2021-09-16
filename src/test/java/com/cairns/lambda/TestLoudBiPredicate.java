package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudBiPredicate}.
 *
 * @author cairnsjr13 - created 14/Sep/2021
 */
public class TestLoudBiPredicate extends LoudFunctionalInterfaceTestBase<LoudBiPredicate<String, String>> {
  public TestLoudBiPredicate() {
    super(LoudBiPredicate.class, oArg1, oArg2);
  }

  @Override
  protected LoudBiPredicate<String, String> succeedingInstance(Indicator indicator) {
    return (left, right) -> indicator.registerArgs(left, right).thenReturn(true);
  }

  @Override
  protected LoudBiPredicate<String, String> failingInstance(Supplier<Exception> toThrow) {
    return (left, right) -> { throw toThrow.get(); };
  }
}
