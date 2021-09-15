package com.cairns.lambda;

import java.util.function.BooleanSupplier;

/**
 * Loud version of {@link BooleanSupplier} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudBooleanSupplier)} method to simplify instance creation with lambdas.
 *
 * @apiNote There shouldn't be autoboxing issues with {@link Boolean}s, but this is for completeness.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
@FunctionalInterface
public interface LoudBooleanSupplier extends BooleanSupplier {
  /**
   * Default implementation will call {@link #getAsBooleanLoudly()}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default boolean getAsBoolean() {
    try {
      return getAsBooleanLoudly();
    }
    catch (RuntimeException e) {
      throw e;
    }
    catch (Exception e) {
      throw new UncaughtCheckedException(e);
    }
  }

  /**
   * See {@link BooleanSupplier#getAsBoolean()}, but with the ability to throw an {@link Exception}.
   */
  boolean getAsBooleanLoudly() throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudBooleanSupplier of(LoudBooleanSupplier loudly) {
    return loudly;
  }
}
