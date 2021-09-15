package com.cairns.lambda;

import java.util.function.BiPredicate;

/**
 * Loud version of the {@link BiPredicate} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudBiPredicate)} method to simplify creation with lambdas.
 *
 * @author cairnsjr13 - created 14/Sep/2021
 *
 * @param <T> the type of the first argument to the predicate
 * @param <U> the type of the second argument the predicate
 */
@FunctionalInterface
public interface LoudBiPredicate<T, U> extends BiPredicate<T, U> {
  /**
   * Default implementation will return {@link #testLoudly(Object, Object)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default boolean test(T t, U u) {
    return LoudBooleanSupplier.of(() -> testLoudly(t, u)).getAsBoolean();
  }

  /**
   * See {@link BiPredicate#test(Object, Object)}, but with the ability to throw an {@link Exception}.
   */
  boolean testLoudly(T t, U u) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T, U> LoudBiPredicate<T, U> of(LoudBiPredicate<T, U> loudly) {
    return loudly;
  }
}
