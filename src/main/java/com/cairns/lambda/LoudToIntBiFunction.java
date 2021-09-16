package com.cairns.lambda;

import java.util.function.ToIntBiFunction;

/**
 * Loud version of the {@link ToIntBiFunction} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudToIntBiFunction)} method to simplify creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive int to/from {@link Integer}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 *
 * @param <T> the type of the first argument to the function
 * @param <U> the type of the second argument to the function
 */
@FunctionalInterface
public interface LoudToIntBiFunction<T, U> extends ToIntBiFunction<T, U> {
  /**
   * Default implementation will return {@link #applyAsIntLoudly(Object, Object)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default int applyAsInt(T t, U u) {
    return LoudIntSupplier.of(() -> applyAsIntLoudly(t, u)).getAsInt();
  }

  /**
   * See {@link ToIntBiFunction#applyAsInt(Object, Object)} , but with the ability to throw an {@link Exception}.
   */
  int applyAsIntLoudly(T t, U u) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T, U> LoudToIntBiFunction<T, U> of(LoudToIntBiFunction<T, U> loudly) {
    return loudly;
  }
}
