package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudObjLongConsumer}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
public class TestLoudObjLongConsumer extends LoudFunctionalInterfaceTestBase<LoudObjLongConsumer<String>> {
  public TestLoudObjLongConsumer() {
    super(LoudObjLongConsumer.class, oArg1, lArg2);
  }

  @Override
  protected LoudObjLongConsumer<String> succeedingInstance(Indicator indicator) {
    return (left, right) -> indicator.registerArgs(left, right);
  }

  @Override
  protected LoudObjLongConsumer<String> failingInstance(Supplier<Exception> toThrow) {
    return (left, right) -> { throw toThrow.get(); };
  }
}
