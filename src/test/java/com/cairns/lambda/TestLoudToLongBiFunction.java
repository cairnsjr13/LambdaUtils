package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudToLongBiFunction}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudToLongBiFunction extends LoudFunctionalInterfaceTestBase<LoudToLongBiFunction<String, String>> {
  public TestLoudToLongBiFunction() {
    super(LoudToLongBiFunction.class, oArg1, oArg2);
  }

  @Override
  protected LoudToLongBiFunction<String, String> succeedingInstance(Indicator indicator) {
    return (left, right) -> indicator.registerArgs(left, right).thenReturn(lReturn);
  }

  @Override
  protected LoudToLongBiFunction<String, String> failingInstance(Supplier<Exception> toThrow) {
    return (left, right) -> { throw toThrow.get(); };
  }
}
