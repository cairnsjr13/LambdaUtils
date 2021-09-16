package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudToIntBiFunction}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudToIntBiFunction extends LoudFunctionalInterfaceTestBase<LoudToIntBiFunction<String, String>> {
  public TestLoudToIntBiFunction() {
    super(LoudToIntBiFunction.class, oArg1, oArg2);
  }

  @Override
  protected LoudToIntBiFunction<String, String> succeedingInstance(Indicator indicator) {
    return (left, right) -> indicator.registerArgs(left, right).thenReturn(iReturn);
  }

  @Override
  protected LoudToIntBiFunction<String, String> failingInstance(Supplier<Exception> toThrow) {
    return (left, right) -> { throw toThrow.get(); };
  }
}
