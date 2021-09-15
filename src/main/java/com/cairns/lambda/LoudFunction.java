package com.cairns.lambda;

import java.util.function.Function;

/**
 * Loud version of the {@link Function} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudFunction)} method to simplify creation with lambdas.
 *
 * @author cairnsjr13 - 14/Sep/2021
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface LoudFunction<T, R> extends Function<T, R> {
  /**
   * Default implementation will return {@link #applyLoudly(Object)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default R apply(T t) {
    return LoudSupplier.of(() -> applyLoudly(t)).get();
  }

  /**
   * See {@link Function#apply(Object)}, but with the ability to throw an {@link Exception}.
   */
  R applyLoudly(T t) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T, R> LoudFunction<T, R> of(LoudFunction<T, R> loudly) {
    return loudly;
  }
}
