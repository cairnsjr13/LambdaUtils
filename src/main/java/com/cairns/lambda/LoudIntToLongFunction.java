package com.cairns.lambda;

import java.util.function.IntToLongFunction;

/**
 * Loud version of {@link IntToLongFunction} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudIntToLongFunction)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive int to/from {@link Integer}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
@FunctionalInterface
public interface LoudIntToLongFunction extends IntToLongFunction {
  /**
   * Default implementation will call {@link #applyAsLongLoudly(int)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default long applyAsLong(int value) {
    return LoudLongSupplier.of(() -> applyAsLongLoudly(value)).getAsLong();
  }

  /**
   * See {@link IntToLongFunction#applyAsLong(int)}, but with the ability to throw an {@link Exception}.
   */
  long applyAsLongLoudly(int value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudIntToLongFunction of(LoudIntToLongFunction loudly) {
    return loudly;
  }
}
