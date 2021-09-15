package com.cairns.lambda;

import java.util.function.LongToIntFunction;

/**
 * Loud version of the {@link LongToIntFunction} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudLongToIntFunction)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive long to/from {@link Long}.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
@FunctionalInterface
public interface LoudLongToIntFunction extends LongToIntFunction {
  /**
   * Default implementation will call {@link #applyAsIntLoudly(long)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default int applyAsInt(long value) {
    return LoudIntSupplier.of(() -> applyAsIntLoudly(value)).getAsInt();
  }

  /**
   * See {@link LongToIntFunction#applyAsInt(long)}, but with the ability to throw an {@link Exception}.
   */
  int applyAsIntLoudly(long value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudLongToIntFunction of(LoudLongToIntFunction loudly) {
    return loudly;
  }
}
