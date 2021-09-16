package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudDoubleSupplier}.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
public class TestLoudDoubleSupplier extends LoudFunctionalInterfaceTestBase<LoudDoubleSupplier> {
  public TestLoudDoubleSupplier() {
    super(LoudDoubleSupplier.class);
  }

  @Override
  protected LoudDoubleSupplier succeedingInstance(Indicator indicator) {
    return () -> indicator.registerArgs().thenReturn(dReturn);
  }

  @Override
  protected LoudDoubleSupplier failingInstance(Supplier<Exception> toThrow) {
    return () -> { throw toThrow.get(); };
  }
}
