package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudLongSupplier}.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
public class TestLoudLongSupplier extends LoudFunctionalInterfaceTestBase<LoudLongSupplier> {
  public TestLoudLongSupplier() {
    super(LoudLongSupplier.class);
  }

  @Override
  protected LoudLongSupplier succeedingInstance(Indicator indicator) {
    return () -> indicator.registerArgs().thenReturn(lReturn);
  }

  @Override
  protected LoudLongSupplier failingInstance(Supplier<Exception> toThrow) {
    return () -> { throw toThrow.get(); };
  }
}
