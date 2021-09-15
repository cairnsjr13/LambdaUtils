package com.cairns.lambda;

import java.util.function.LongToDoubleFunction;

/**
 * Loud version of the {@link LongToDoubleFunction} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudLongToDoubleFunction)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive long to/from {@link Long}.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
@FunctionalInterface
public interface LoudLongToDoubleFunction extends LongToDoubleFunction {
  /**
   * Default implementation will call {@link #applyAsDoubleLoudly(long)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default double applyAsDouble(long value) {
    return LoudDoubleSupplier.of(() -> applyAsDoubleLoudly(value)).getAsDouble();
  }

  /**
   * See {@link LongToDoubleFunction#applyAsDouble(long)}, but with the ability to throw an {@link Exception}.
   */
  double applyAsDoubleLoudly(long value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudLongToDoubleFunction of(LoudLongToDoubleFunction loudly) {
    return loudly;
  }
}
