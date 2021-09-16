package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudToIntFunction}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudToIntFunction extends LoudFunctionalInterfaceTestBase<LoudToIntFunction<String>> {
  public TestLoudToIntFunction() {
    super(LoudToIntFunction.class, oArg1);
  }

  @Override
  protected LoudToIntFunction<String> succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(iReturn);
  }

  @Override
  protected LoudToIntFunction<String> failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
