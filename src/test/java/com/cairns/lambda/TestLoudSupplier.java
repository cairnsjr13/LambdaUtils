package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudSupplier}.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
public class TestLoudSupplier extends LoudFunctionalInterfaceTestBase<LoudSupplier<String>> {
  public TestLoudSupplier() {
    super(LoudSupplier.class);
  }

  @Override
  protected LoudSupplier<String> succeedingInstance(Indicator indicator) {
    return () -> indicator.registerArgs().thenReturn(oReturn);
  }

  @Override
  protected LoudSupplier<String> failingInstance(Supplier<Exception> toThrow) {
    return () -> { throw toThrow.get(); };
  }
}
