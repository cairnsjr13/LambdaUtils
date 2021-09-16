package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudBiFunction}.
 *
 * @author cairnsjr13 - created 14/Sep/2021
 */
public class TestLoudBiFunction extends LoudFunctionalInterfaceTestBase<LoudBiFunction<String, String, String>> {
  public TestLoudBiFunction() {
    super(LoudBiFunction.class, oArg1, oArg2);
  }

  @Override
  protected LoudBiFunction<String, String, String> succeedingInstance(Indicator indicator) {
    return (left, right) -> indicator.registerArgs(left, right).thenReturn(oReturn);
  }

  @Override
  protected LoudBiFunction<String, String, String> failingInstance(Supplier<Exception> toThrow) {
    return (left, right) -> { throw toThrow.get(); };
  }
}
