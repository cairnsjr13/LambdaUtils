package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudIntUnaryOperator}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudIntUnaryOperator extends LoudFunctionalInterfaceTestBase<LoudIntUnaryOperator> {
  public TestLoudIntUnaryOperator() {
    super(LoudIntUnaryOperator.class, iArg1);
  }

  @Override
  protected LoudIntUnaryOperator succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(iReturn);
  }

  @Override
  protected LoudIntUnaryOperator failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
