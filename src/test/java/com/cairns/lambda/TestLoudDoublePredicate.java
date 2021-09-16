package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudDoublePredicate}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudDoublePredicate extends LoudFunctionalInterfaceTestBase<LoudDoublePredicate> {
  public TestLoudDoublePredicate() {
    super(LoudDoublePredicate.class, dArg1);
  }

  @Override
  protected LoudDoublePredicate succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(true);
  }

  @Override
  protected LoudDoublePredicate failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
