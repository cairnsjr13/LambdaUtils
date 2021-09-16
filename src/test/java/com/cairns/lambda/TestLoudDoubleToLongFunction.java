package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudDoubleToLongFunction}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudDoubleToLongFunction extends LoudFunctionalInterfaceTestBase<LoudDoubleToLongFunction> {
  public TestLoudDoubleToLongFunction() {
    super(LoudDoubleToLongFunction.class, dArg1);
  }

  @Override
  protected LoudDoubleToLongFunction succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(lReturn);
  }

  @Override
  protected LoudDoubleToLongFunction failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
