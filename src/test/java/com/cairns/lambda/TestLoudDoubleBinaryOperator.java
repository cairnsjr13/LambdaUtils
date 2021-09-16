package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudDoubleBinaryOperator}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudDoubleBinaryOperator extends LoudFunctionalInterfaceTestBase<LoudDoubleBinaryOperator> {
  public TestLoudDoubleBinaryOperator() {
    super(LoudDoubleBinaryOperator.class, dArg1, dArg2);
  }

  @Override
  protected LoudDoubleBinaryOperator succeedingInstance(Indicator indicator) {
    return (left, right) -> indicator.registerArgs(left, right).thenReturn(dReturn);
  }

  @Override
  protected LoudDoubleBinaryOperator failingInstance(Supplier<Exception> toThrow) {
    return (left, right) -> { throw toThrow.get(); };
  }
}
