package com.cairns.lambda;

import java.util.function.IntSupplier;

/**
 * Loud version of {@link IntSupplier} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudIntSupplier)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive int to/from {@link Integer}.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
@FunctionalInterface
public interface LoudIntSupplier extends IntSupplier {
  /**
   * Default implementation will call {@link #getAsIntLoudly()}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default int getAsInt() {
    try {
      return getAsIntLoudly();
    }
    catch (RuntimeException e) {
      throw e;
    }
    catch (Exception e) {
      throw new UncaughtCheckedException(e);
    }
  }

  /**
   * See {@link IntSupplier#getAsInt()}, but with the ability to throw an {@link Exception}.
   */
  int getAsIntLoudly() throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudIntSupplier of(LoudIntSupplier loudly) {
    return loudly;
  }
}
