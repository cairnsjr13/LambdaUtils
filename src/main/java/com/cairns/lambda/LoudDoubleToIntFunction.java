package com.cairns.lambda;

import java.util.function.DoubleToIntFunction;

/**
 * Loud version of the {@link DoubleToIntFunction} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudDoubleToIntFunction)} method to simplify creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive double to/from {@link Double}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
@FunctionalInterface
public interface LoudDoubleToIntFunction extends DoubleToIntFunction {
  /**
   * Default implementation will return {@link #applyAsIntLoudly(double)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default int applyAsInt(double value) {
    return LoudIntSupplier.of(() -> applyAsIntLoudly(value)).getAsInt();
  }

  /**
   * See {@link DoubleToIntFunction#applyAsInt(double)}, but with the ability to throw an {@link Exception}.
   */
  int applyAsIntLoudly(double value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudDoubleToIntFunction of(LoudDoubleToIntFunction loudly) {
    return loudly;
  }
}
