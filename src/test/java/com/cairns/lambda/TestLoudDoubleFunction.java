package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudDoubleFunction}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudDoubleFunction extends LoudFunctionalInterfaceTestBase<LoudDoubleFunction<String>> {
  public TestLoudDoubleFunction() {
    super(LoudDoubleFunction.class, dArg1);
  }

  @Override
  protected LoudDoubleFunction<String> succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(oReturn);
  }

  @Override
  protected LoudDoubleFunction<String> failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
