package com.cairns.lambda;

import java.util.function.IntFunction;

/**
 * Loud version of {@link IntFunction} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudIntFunction)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive int to/from {@link Integer}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 *
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface LoudIntFunction<R> extends IntFunction<R> {
  /**
   * Default implementation will call {@link #applyLoudly(int)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default R apply(int value) {
    return LoudSupplier.of(() -> applyLoudly(value)).get();
  }

  /**
   * See {@link IntFunction#apply(int)}, but with the ability to throw an {@link Exception}.
   */
  R applyLoudly(int value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <R> LoudIntFunction<R> of(LoudIntFunction<R> loudly) {
    return loudly;
  }
}
