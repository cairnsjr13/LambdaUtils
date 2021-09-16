package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudFunction}.
 *
 * @author cairnsjr13 - created 14/Sep/2021
 */
public class TestLoudFunction extends LoudFunctionalInterfaceTestBase<LoudFunction<String, String>> {
  public TestLoudFunction() {
    super(LoudFunction.class, oArg1);
  }

  @Override
  protected LoudFunction<String, String> succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(oReturn);
  }

  @Override
  protected LoudFunction<String, String> failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
