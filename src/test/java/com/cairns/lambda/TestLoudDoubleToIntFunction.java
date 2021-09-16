package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudDoubleToIntFunction}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudDoubleToIntFunction extends LoudFunctionalInterfaceTestBase<LoudDoubleToIntFunction> {
  public TestLoudDoubleToIntFunction() {
    super(LoudDoubleToIntFunction.class, dArg1);
  }

  @Override
  protected LoudDoubleToIntFunction succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(iReturn);
  }

  @Override
  protected LoudDoubleToIntFunction failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
