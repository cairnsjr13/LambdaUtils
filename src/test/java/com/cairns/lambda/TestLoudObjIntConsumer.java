package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudObjIntConsumer}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudObjIntConsumer extends LoudFunctionalInterfaceTestBase<LoudObjIntConsumer<String>> {
  public TestLoudObjIntConsumer() {
    super(LoudObjIntConsumer.class, oArg1, iArg2);
  }

  @Override
  protected LoudObjIntConsumer<String> succeedingInstance(Indicator indicator) {
    return (left, right) -> indicator.registerArgs(left, right);
  }

  @Override
  protected LoudObjIntConsumer<String> failingInstance(Supplier<Exception> toThrow) {
    return (left, right) -> { throw toThrow.get(); };
  }
}
