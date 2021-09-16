package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudConsumer}.
 *
 * @author cairnsjr13 - created 14/Sep/2021
 */
public class TestLoudConsumer extends LoudFunctionalInterfaceTestBase<LoudConsumer<String>> {
  public TestLoudConsumer() {
    super(LoudConsumer.class, oArg1);
  }

  @Override
  protected LoudConsumer<String> succeedingInstance(Indicator indicator) {
    return (arg) -> indicator.registerArgs(arg);
  }

  @Override
  protected LoudConsumer<String> failingInstance(Supplier<Exception> toThrow) {
    return (arg) -> { throw toThrow.get(); };
  }
}
