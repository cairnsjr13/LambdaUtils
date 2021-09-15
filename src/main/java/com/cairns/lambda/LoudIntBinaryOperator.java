package com.cairns.lambda;

import java.util.function.IntBinaryOperator;

/**
 * Loud version of {@link IntBinaryOperator} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudIntBinaryOperator)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive int to/from {@link Integer}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
@FunctionalInterface
public interface LoudIntBinaryOperator extends IntBinaryOperator {
  /**
   * Default implementation will call {@link #applyAsIntLoudly(int, int)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default int applyAsInt(int left, int right) {
    return LoudIntSupplier.of(() -> applyAsIntLoudly(left, right)).getAsInt();
  }

  /**
   * See {@link IntBinaryOperator#applyAsInt(int, int)}, but with the ability to throw an {@link Exception}.
   */
  int applyAsIntLoudly(int left, int right) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudIntBinaryOperator of(LoudIntBinaryOperator loudly) {
    return loudly;
  }
}
