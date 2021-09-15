package com.cairns.lambda;

import java.util.function.DoublePredicate;

/**
 * Loud version of the {@link DoublePredicate} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudDoublePredicate)} method to simplify creation with lambdas.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
@FunctionalInterface
public interface LoudDoublePredicate extends DoublePredicate {
  /**
   * Default implementation will return {@link #testLoudly(double)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default boolean test(double value) {
    return LoudBooleanSupplier.of(() -> testLoudly(value)).getAsBoolean();
  }

  /**
   * See {@link DoublePredicate#test(double)}, but with the ability to throw an {@link Exception}.
   */
  boolean testLoudly(double value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudDoublePredicate of(LoudDoublePredicate loudly) {
    return loudly;
  }
}
