package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudLongConsumer}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudLongConsumer extends LoudFunctionalInterfaceTestBase<LoudLongConsumer> {
  public TestLoudLongConsumer() {
    super(LoudLongConsumer.class, lArg1);
  }

  @Override
  protected LoudLongConsumer succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg);
  }

  @Override
  protected LoudLongConsumer failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
