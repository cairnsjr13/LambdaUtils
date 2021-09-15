package com.cairns.lambda;

import java.util.function.IntToDoubleFunction;

/**
 * Loud version of {@link IntToDoubleFunction} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudIntToDoubleFunction)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive int to/from {@link Integer}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
@FunctionalInterface
public interface LoudIntToDoubleFunction extends IntToDoubleFunction {
  /**
   * Default implementation will call {@link #applyAsDoubleLoudly(int)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default double applyAsDouble(int value) {
    return LoudDoubleSupplier.of(() -> applyAsDoubleLoudly(value)).getAsDouble();
  }

  /**
   * See {@link IntToDoubleFunction#applyAsDouble(int)}, but with the ability to throw an {@link Exception}.
   */
  double applyAsDoubleLoudly(int value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudIntToDoubleFunction of(LoudIntToDoubleFunction loudly) {
    return loudly;
  }
}
