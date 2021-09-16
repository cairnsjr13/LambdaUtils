package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudLongPredicate}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudLongPredicate extends LoudFunctionalInterfaceTestBase<LoudLongPredicate> {
  public TestLoudLongPredicate() {
    super(LoudLongPredicate.class, lArg1);
  }

  @Override
  protected LoudLongPredicate succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(true);
  }

  @Override
  protected LoudLongPredicate failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
