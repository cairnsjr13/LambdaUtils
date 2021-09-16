package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudIntPredicate}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudIntPredicate extends LoudFunctionalInterfaceTestBase<LoudIntPredicate> {
  public TestLoudIntPredicate() {
    super(LoudIntPredicate.class, iArg1);
  }

  @Override
  protected LoudIntPredicate succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(true);
  }

  @Override
  protected LoudIntPredicate failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
