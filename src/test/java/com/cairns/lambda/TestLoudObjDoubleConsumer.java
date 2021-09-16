package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudObjDoubleConsumer}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudObjDoubleConsumer extends LoudFunctionalInterfaceTestBase<LoudObjDoubleConsumer<String>> {
  public TestLoudObjDoubleConsumer() {
    super(LoudObjDoubleConsumer.class, oArg1, dArg2);
  }

  @Override
  protected LoudObjDoubleConsumer<String> succeedingInstance(Indicator indicator) {
    return (left, right) -> indicator.registerArgs(left, right);
  }

  @Override
  protected LoudObjDoubleConsumer<String> failingInstance(Supplier<Exception> toThrow) {
    return (left, right) -> { throw toThrow.get(); };
  }
}
