package com.cairns.lambda;

import java.util.function.Predicate;

/**
 * Loud version of the {@link Predicate} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudPredicate)} method to simplify creation with lambdas.
 *
 * @author cairnsjr13 - created 14/Sep/2021
 *
 * @param <T> the type of the input to the predicate
 */
@FunctionalInterface
public interface LoudPredicate<T> extends Predicate<T> {
  /**
   * Default implementation will return {@link #testLoudly(Object)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default boolean test(T t) {
    return LoudBooleanSupplier.of(() -> testLoudly(t)).getAsBoolean();
  }

  /**
   * See {@link Predicate#test(Object)}, but with the ability to throw an {@link Exception}.
   */
  boolean testLoudly(T t) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T> LoudPredicate<T> of(LoudPredicate<T> loudly) {
    return loudly;
  }
}
