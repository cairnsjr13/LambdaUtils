package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudIntSupplier}.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
public class TestLoudIntSupplier extends LoudFunctionalInterfaceTestBase<LoudIntSupplier> {
  public TestLoudIntSupplier() {
    super(LoudIntSupplier.class);
  }

  @Override
  protected LoudIntSupplier succeedingInstance(Indicator indicator) {
    return () -> indicator.registerArgs().thenReturn(iReturn);
  }

  @Override
  protected LoudIntSupplier failingInstance(Supplier<Exception> toThrow) {
    return () -> { throw toThrow.get(); };
  }
}
