package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudLongFunction}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudLongFunction extends LoudFunctionalInterfaceTestBase<LoudLongFunction<String>> {
  public TestLoudLongFunction() {
    super(LoudLongFunction.class, lArg1);
  }

  @Override
  protected LoudLongFunction<String> succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(oReturn);
  }

  @Override
  protected LoudLongFunction<String> failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
