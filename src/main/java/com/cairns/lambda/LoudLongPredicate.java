package com.cairns.lambda;

import java.util.function.LongPredicate;

/**
 * Loud version of the {@link LongPredicate} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudLongPredicate)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive long to/from {@link Long}.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
@FunctionalInterface
public interface LoudLongPredicate extends LongPredicate {
  /**
   * Default implementation will call {@link #testLoudly(long)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default boolean test(long value) {
    return LoudBooleanSupplier.of(() -> testLoudly(value)).getAsBoolean();
  }

  /**
   * See {@link LongPredicate#test(long)}, but with the ability to throw an {@link Exception}.
   */
  boolean testLoudly(long value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudLongPredicate of(LoudLongPredicate loudly) {
    return loudly;
  }
}
