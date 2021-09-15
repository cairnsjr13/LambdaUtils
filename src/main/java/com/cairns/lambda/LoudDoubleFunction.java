package com.cairns.lambda;

import java.util.function.DoubleFunction;

/**
 * Loud version of the {@link DoubleFunction} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudDoubleFunction)} method to simplify creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive double to/from {@link Double}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 *
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface LoudDoubleFunction<R> extends DoubleFunction<R> {
  /**
   * Default implementation will return {@link #applyLoudly(double)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default R apply(double value) {
    return LoudSupplier.of(() -> applyLoudly(value)).get();
  }

  /**
   * See {@link DoubleFunction#apply(double)}, but with the ability to throw an {@link Exception}.
   */
  R applyLoudly(double value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <R> LoudDoubleFunction<R> of(LoudDoubleFunction<R> loudly) {
    return loudly;
  }
}
