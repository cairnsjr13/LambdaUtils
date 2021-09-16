package com.cairns.lambda;

import java.util.function.ToIntFunction;

/**
 * Loud version of the {@link ToIntFunction} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudToIntFunction)} method to simplify creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive int to/from {@link Integer}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 *
 * @param <T> the type of the input to the function
 */
@FunctionalInterface
public interface LoudToIntFunction<T> extends ToIntFunction<T> {
  /**
   * Default implementation will return {@link #applyAsIntLoudly(Object)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default int applyAsInt(T value) {
    return LoudIntSupplier.of(() -> applyAsIntLoudly(value)).getAsInt();
  }

  /**
   * See {@link ToIntFunction#applyAsInt(Object)}, but with the ability to throw an {@link Exception}.
   */
  int applyAsIntLoudly(T value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T> LoudToIntFunction<T> of(LoudToIntFunction<T> loudly) {
    return loudly;
  }
}
