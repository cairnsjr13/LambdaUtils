package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudLongUnaryOperator}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudLongUnaryOperator extends LoudFunctionalInterfaceTestBase<LoudLongUnaryOperator> {
  public TestLoudLongUnaryOperator() {
    super(LoudLongUnaryOperator.class, lArg1);
  }

  @Override
  protected LoudLongUnaryOperator succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(lReturn);
  }

  @Override
  protected LoudLongUnaryOperator failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
