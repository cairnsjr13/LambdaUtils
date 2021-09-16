package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudLongBinaryOperator}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudLongBinaryOperator extends LoudFunctionalInterfaceTestBase<LoudLongBinaryOperator> {
  public TestLoudLongBinaryOperator() {
    super(LoudLongBinaryOperator.class, lArg1, lArg2);
  }

  @Override
  protected LoudLongBinaryOperator succeedingInstance(Indicator indicator) {
    return (left, right) -> indicator.registerArgs(left, right).thenReturn(lReturn);
  }

  @Override
  protected LoudLongBinaryOperator failingInstance(Supplier<Exception> toThrow) {
    return (left, right) -> { throw toThrow.get(); };
  }
}
