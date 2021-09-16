package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudToDoubleBiFunction}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudToDoubleBiFunction extends LoudFunctionalInterfaceTestBase<LoudToDoubleBiFunction<String, String>>
{
  public TestLoudToDoubleBiFunction() {
    super(LoudToDoubleBiFunction.class, oArg1, oArg2);
  }

  @Override
  protected LoudToDoubleBiFunction<String, String> succeedingInstance(Indicator indicator) {
    return (left, right) -> indicator.registerArgs(left, right).thenReturn(dReturn);
  }

  @Override
  protected LoudToDoubleBiFunction<String, String> failingInstance(Supplier<Exception> toThrow) {
    return (left, right) -> { throw toThrow.get(); };
  }
}
