package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudIntConsumer}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudIntConsumer extends LoudFunctionalInterfaceTestBase<LoudIntConsumer> {
  public TestLoudIntConsumer() {
    super(LoudIntConsumer.class, iArg1);
  }

  @Override
  protected LoudIntConsumer succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg);
  }

  @Override
  protected LoudIntConsumer failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
