package com.cairns.lambda;

import java.util.function.ToDoubleFunction;

/**
 * Loud version of the {@link ToDoubleFunction} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudToDoubleFunction)} method to simplify creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive double to/from {@link Double}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 *
 * @param <T> the type of the input to the function
 */
@FunctionalInterface
public interface LoudToDoubleFunction<T> extends ToDoubleFunction<T> {
  /**
   * Default implementation will return {@link #applyAsDoubleLoudly(Object)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default double applyAsDouble(T value) {
    return LoudDoubleSupplier.of(() -> applyAsDoubleLoudly(value)).getAsDouble();
  }

  /**
   * See {@link ToDoubleFunction#applyAsDouble(Object)}, but with the ability to throw an {@link Exception}.
   */
  double applyAsDoubleLoudly(T value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T> LoudToDoubleFunction<T> of(LoudToDoubleFunction<T> loudly) {
    return loudly;
  }
}
