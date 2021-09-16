package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudUnaryOperator}.
 *
 * @author cairnsjr13 - created 14/Sep/2021
 */
public class TestLoudUnaryOperator extends LoudFunctionalInterfaceTestBase<LoudUnaryOperator<String>> {
  public TestLoudUnaryOperator() {
    super(LoudUnaryOperator.class, oArg1);
  }

  @Override
  protected LoudUnaryOperator<String> succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(oReturn);
  }

  @Override
  protected LoudUnaryOperator<String> failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
