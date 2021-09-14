package com.cairns.lambda;

import java.util.function.DoubleSupplier;

/**
 * Loud version of the {@link DoubleSupplier} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudDoubleSupplier)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive double to/from {@link Double}.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
@FunctionalInterface
public interface LoudDoubleSupplier extends DoubleSupplier{
  /**
   * Default implementation will call {@link #getAsDoubleLoudly()}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default double getAsDouble() {
    try {
      return getAsDoubleLoudly();
    }
    catch (RuntimeException e) {
      throw e;
    }
    catch (Exception e) {
      throw new UncaughtCheckedException(e);
    }
  }

  /**
   * See {@link DoubleSupplier#getAsDouble()}, but with the ability to throw an {@link Exception}.
   */
  double getAsDoubleLoudly() throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudDoubleSupplier of(LoudDoubleSupplier loudly) {
    return loudly;
  }
}
