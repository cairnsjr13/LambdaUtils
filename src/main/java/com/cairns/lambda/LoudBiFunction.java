package com.cairns.lambda;

import java.util.function.BiFunction;

/**
 * Loud version of the {@link BiFunction} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudBiFunction)} method to simplify creation with lambdas.
 *
 * @author cairnsjr13 - created 14/Sep/2021
 *
 * @param <T> the type of the first argument to the function
 * @param <U> the type of the second argument to the function
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface LoudBiFunction<T, U, R> extends BiFunction<T, U, R> {
  /**
   * Default implementation will return {@link #applyLoudly(Object, Object)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default R apply(T t, U u) {
    return LoudSupplier.of(() -> applyLoudly(t, u)).get();
  }

  /**
   * See {@link BiFunction#apply(Object, Object)}, but with the ability to throw an {@link Exception}.
   */
  R applyLoudly(T t, U u) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T, U, R> LoudBiFunction<T, U, R> of(LoudBiFunction<T, U, R> loudly) {
    return loudly;
  }
}
