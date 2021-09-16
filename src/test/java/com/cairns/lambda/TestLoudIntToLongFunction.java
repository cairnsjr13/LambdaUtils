package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudIntToLongFunction}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudIntToLongFunction extends LoudFunctionalInterfaceTestBase<LoudIntToLongFunction> {
  public TestLoudIntToLongFunction() {
    super(LoudIntToLongFunction.class, iArg1);
  }

  @Override
  protected LoudIntToLongFunction succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(lReturn);
  }

  @Override
  protected LoudIntToLongFunction failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
