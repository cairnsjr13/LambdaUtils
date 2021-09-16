package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudBooleanSupplier}.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
public class TestLoudBooleanSupplier extends LoudFunctionalInterfaceTestBase<LoudBooleanSupplier> {
  public TestLoudBooleanSupplier() {
    super(LoudBooleanSupplier.class);
  }

  @Override
  protected LoudBooleanSupplier succeedingInstance(Indicator indicator) {
    return () -> indicator.registerArgs().thenReturn(true);
  }

  @Override
  protected LoudBooleanSupplier failingInstance(Supplier<Exception> toThrow) {
    return () -> { throw toThrow.get(); };
  }
}
