package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Test class for {@link LoudRunnable}.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
public class TestLoudRunnable extends LoudFunctionalInterfaceTestBase<LoudRunnable> {
  public TestLoudRunnable() {
    super(LoudRunnable.class);
  }

  @Override
  protected LoudRunnable succeedingInstance(Indicator indicator) {
    return () -> indicator.registerArgs();
  }

  @Override
  protected LoudRunnable failingInstance(Supplier<Exception> toThrow) {
    return () -> { throw toThrow.get(); };
  }
}
