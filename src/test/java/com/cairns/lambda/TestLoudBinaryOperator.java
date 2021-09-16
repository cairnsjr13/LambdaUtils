package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudBinaryOperator}.
 *
 * @author cairnsjr13 - created 14/Sep/2021
 */
public class TestLoudBinaryOperator extends LoudFunctionalInterfaceTestBase<LoudBinaryOperator<String>> {
  public TestLoudBinaryOperator() {
    super(LoudBinaryOperator.class, oArg1, oArg2);
  }

  @Override
  protected LoudBinaryOperator<String> succeedingInstance(Indicator indicator) {
    return (left, right) -> indicator.registerArgs(left, right).thenReturn(oReturn);
  }

  @Override
  protected LoudBinaryOperator<String> failingInstance(Supplier<Exception> toThrow) {
    return (left, right) -> { throw toThrow.get(); };
  }
}
