package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudIntToDoubleFunction}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudIntToDoubleFunction extends LoudFunctionalInterfaceTestBase<LoudIntToDoubleFunction> {
  public TestLoudIntToDoubleFunction() {
    super(LoudIntToDoubleFunction.class, iArg1);
  }

  @Override
  protected LoudIntToDoubleFunction succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(dReturn);
  }

  @Override
  protected LoudIntToDoubleFunction failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
