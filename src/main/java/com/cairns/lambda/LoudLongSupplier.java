package com.cairns.lambda;

import java.util.function.LongSupplier;

/**
 * Loud version of the {@link LongSupplier} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudLongSupplier)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive long to/from {@link Long}.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
@FunctionalInterface
public interface LoudLongSupplier extends LongSupplier {
  /**
   * Default implementation will call {@link #getAsLongLoudly()}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default long getAsLong() {
    try {
      return getAsLongLoudly();
    }
    catch (RuntimeException e) {
      throw e;
    }
    catch (Exception e) {
      throw new UncaughtCheckedException(e);
    }
  }

  /**
   * See {@link LongSupplier#getAsLong()}, but with the ability to throw an {@link Exception}.
   */
  long getAsLongLoudly() throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudLongSupplier of(LoudLongSupplier loudly) {
    return loudly;
  }
}
