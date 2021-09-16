package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudDoubleUnaryOperator}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudDoubleUnaryOperator extends LoudFunctionalInterfaceTestBase<LoudDoubleUnaryOperator> {
  public TestLoudDoubleUnaryOperator() {
    super(LoudDoubleUnaryOperator.class, dArg1);
  }

  @Override
  protected LoudDoubleUnaryOperator succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(dReturn);
  }

  @Override
  protected LoudDoubleUnaryOperator failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
