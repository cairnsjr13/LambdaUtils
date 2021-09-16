package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudDoubleConsumer}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudDoubleConsumer extends LoudFunctionalInterfaceTestBase<LoudDoubleConsumer> {
  public TestLoudDoubleConsumer() {
    super(LoudDoubleConsumer.class, dArg1);
  }

  @Override
  protected LoudDoubleConsumer succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg);
  }

  @Override
  protected LoudDoubleConsumer failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
