package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudIntBinaryOperator}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudIntBinaryOperator extends LoudFunctionalInterfaceTestBase<LoudIntBinaryOperator> {
  public TestLoudIntBinaryOperator() {
    super(LoudIntBinaryOperator.class, iArg1, iArg2);
  }

  @Override
  protected LoudIntBinaryOperator succeedingInstance(Indicator indicator) {
    return (left, right) -> indicator.registerArgs(left, right).thenReturn(iReturn);
  }

  @Override
  protected LoudIntBinaryOperator failingInstance(Supplier<Exception> toThrow) {
    return (left, right) -> { throw toThrow.get(); };
  }
}
