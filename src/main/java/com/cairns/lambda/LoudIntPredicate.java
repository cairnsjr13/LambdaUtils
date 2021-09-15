package com.cairns.lambda;

import java.util.function.IntPredicate;

/**
 * Loud version of {@link IntPredicate} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudIntPredicate)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive int to/from {@link Integer}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
@FunctionalInterface
public interface LoudIntPredicate extends IntPredicate {
  /**
   * Default implementation will call {@link #testLoudly(int)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default boolean test(int value) {
    return LoudBooleanSupplier.of(() -> testLoudly(value)).getAsBoolean();
  }

  /**
   * See {@link IntPredicate#test(int)}, but with the ability to throw an {@link Exception}.
   */
  boolean testLoudly(int value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudIntPredicate of(LoudIntPredicate loudly) {
    return loudly;
  }
}
