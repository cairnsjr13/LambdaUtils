package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudToDoubleFunction}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudToDoubleFunction extends LoudFunctionalInterfaceTestBase<LoudToDoubleFunction<String>> {
  public TestLoudToDoubleFunction() {
    super(LoudToDoubleFunction.class, oArg1);
  }

  @Override
  protected LoudToDoubleFunction<String> succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(dReturn);
  }

  @Override
  protected LoudToDoubleFunction<String> failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
