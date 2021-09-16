package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudLongToDoubleFunction}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudLongToDoubleFunction extends LoudFunctionalInterfaceTestBase<LoudLongToDoubleFunction> {
  public TestLoudLongToDoubleFunction() {
    super(LoudLongToDoubleFunction.class, lArg1);
  }

  @Override
  protected LoudLongToDoubleFunction succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(dReturn);
  }

  @Override
  protected LoudLongToDoubleFunction failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
