package com.cairns.lambda;

import java.util.function.LongFunction;

/**
 * Loud version of the {@link LongFunction} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudLongFunction)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive long to/from {@link Long}.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 *
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface LoudLongFunction<R> extends LongFunction<R> {
  /**
   * Default implementation will call {@link #applyLoudly(long)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default R apply(long value) {
    return LoudSupplier.of(() -> applyLoudly(value)).get();
  }

  /**
   * See {@link LongFunction#apply(long)}, but with the ability to throw an {@link Exception}.
   */
  R applyLoudly(long value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <R> LoudLongFunction<R> of(LoudLongFunction<R> loudly) {
    return loudly;
  }
}
