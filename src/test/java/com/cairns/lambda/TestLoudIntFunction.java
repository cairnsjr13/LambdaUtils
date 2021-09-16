package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudIntFunction}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudIntFunction extends LoudFunctionalInterfaceTestBase<LoudIntFunction<String>> {
  public TestLoudIntFunction() {
    super(LoudIntFunction.class, iArg1);
  }

  @Override
  protected LoudIntFunction<String> succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(oReturn);
  }

  @Override
  protected LoudIntFunction<String> failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
