package com.cairns.lambda;

import java.util.function.ToLongBiFunction;

/**
 * Loud version of the {@link ToLongBiFunction} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudToLongBiFunction)} method to simplify creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive long to/from {@link Long}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 *
 * @param <T> the type of the first argument to the function
 * @param <U> the type of the second argument to the function
 */
@FunctionalInterface
public interface LoudToLongBiFunction<T, U> extends ToLongBiFunction<T, U> {
  /**
   * Default implementation will return {@link #applyAsLongLoudly(Object, Object)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default long applyAsLong(T t, U u) {
    return LoudLongSupplier.of(() -> applyAsLongLoudly(t, u)).getAsLong();
  }

  /**
   * See {@link ToLongBiFunction#applyAsLong(Object, Object)}, but with the ability to throw an {@link Exception}.
   */
  long applyAsLongLoudly(T t, U u) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T, U> LoudToLongBiFunction<T, U> of(LoudToLongBiFunction<T, U> loudly) {
    return loudly;
  }
}
