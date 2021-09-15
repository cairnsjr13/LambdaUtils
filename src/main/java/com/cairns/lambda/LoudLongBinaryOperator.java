package com.cairns.lambda;

import java.util.function.LongBinaryOperator;

/**
 * Loud version of the {@link LongBinaryOperator} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudLongBinaryOperator)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive long to/from {@link Long}.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
@FunctionalInterface
public interface LoudLongBinaryOperator extends LongBinaryOperator {
  /**
   * Default implementation will call {@link #applyAsLongLoudly(long, long)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default long applyAsLong(long left, long right) {
    return LoudLongSupplier.of(() -> applyAsLongLoudly(left, right)).getAsLong();
  }

  /**
   * See {@link LongBinaryOperator#applyAsLong(long, long)}, but with the ability to throw an {@link Exception}.
   */
  long applyAsLongLoudly(long left, long right) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudLongBinaryOperator of(LoudLongBinaryOperator loudly) {
    return loudly;
  }
}
