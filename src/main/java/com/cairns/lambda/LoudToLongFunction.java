package com.cairns.lambda;

import java.util.function.ToLongFunction;

/**
 * Loud version of the {@link ToLongFunction} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudToLongFunction)} method to simplify creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive long to/from {@link Long}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 *
 * @param <T> the type of the input to the function
 */
@FunctionalInterface
public interface LoudToLongFunction<T> extends ToLongFunction<T> {
  /**
   * Default implementation will return {@link #applyAsLongLoudly(Object)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default long applyAsLong(T value) {
    return LoudLongSupplier.of(() -> applyAsLongLoudly(value)).getAsLong();
  }

  /**
   * See {@link ToLongFunction#applyAsLong(Object)}, but with the ability to throw an {@link Exception}.
   */
  long applyAsLongLoudly(T value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T> LoudToLongFunction<T> of(LoudToLongFunction<T> loudly) {
    return loudly;
  }
}
