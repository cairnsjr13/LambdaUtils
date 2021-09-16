package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudBiConsumer}.
 *
 * @author cairnsjr13 - created 14/Sep/2021
 */
public class TestLoudBiConsumer extends LoudFunctionalInterfaceTestBase<LoudBiConsumer<String, String>> {
  public TestLoudBiConsumer() {
    super(LoudBiConsumer.class, oArg1, oArg2);
  }

  @Override
  protected LoudBiConsumer<String, String> succeedingInstance(Indicator indicator) {
    return (left, right) -> indicator.registerArgs(left, right);
  }

  @Override
  protected LoudBiConsumer<String, String> failingInstance(Supplier<Exception> toThrow) {
    return (left, right) -> { throw toThrow.get(); };
  }
}
