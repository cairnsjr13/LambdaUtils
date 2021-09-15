package com.cairns.lambda;

import java.util.function.DoubleToLongFunction;

/**
 * Loud version of the {@link DoubleToLongFunction} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudDoubleToLongFunction)} method to simplify creation with lambdas.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
@FunctionalInterface
public interface LoudDoubleToLongFunction extends DoubleToLongFunction {
  /**
   * Default implementation will return {@link #applyAsLongLoudly(double)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default long applyAsLong(double value) {
    return LoudLongSupplier.of(() -> applyAsLongLoudly(value)).getAsLong();
  }

  /**
   * See {@link DoubleToLongFunction#applyAsLong(double)}, but with the ability to throw an {@link Exception}.
   */
  long applyAsLongLoudly(double value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudDoubleToLongFunction of(LoudDoubleToLongFunction loudly) {
    return loudly;
  }
}
