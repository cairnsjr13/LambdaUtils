package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudLongToIntFunction}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudLongToIntFunction extends LoudFunctionalInterfaceTestBase<LoudLongToIntFunction> {
  public TestLoudLongToIntFunction() {
    super(LoudLongToIntFunction.class, lArg1);
  }

  @Override
  protected LoudLongToIntFunction succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg).thenReturn(iReturn);
  }

  @Override
  protected LoudLongToIntFunction failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
