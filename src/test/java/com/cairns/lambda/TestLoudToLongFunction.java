package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudToLongFunction}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudToLongFunction extends LoudFunctionalInterfaceTestBase<LoudToLongFunction<String>> {
  public TestLoudToLongFunction() {
    super(LoudToLongFunction.class, oArg1);
  }

  @Override
  protected LoudToLongFunction<String> succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(lReturn);
  }

  @Override
  protected LoudToLongFunction<String> failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
