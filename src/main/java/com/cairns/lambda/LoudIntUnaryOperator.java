package com.cairns.lambda;

import java.util.function.IntUnaryOperator;

/**
 * Loud version of {@link IntUnaryOperator} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudIntUnaryOperator)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive int to/from {@link Integer}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
@FunctionalInterface
public interface LoudIntUnaryOperator extends IntUnaryOperator {
  /**
   * Default implementation will call {@link #applyAsIntLoudly(int)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default int applyAsInt(int operand) {
    return LoudIntSupplier.of(() -> applyAsIntLoudly(operand)).getAsInt();
  }

  /**
   * See {@link IntUnaryOperator#applyAsInt(int)}, but with the ability to throw an {@link Exception}.
   */
  int applyAsIntLoudly(int operand) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudIntUnaryOperator of(LoudIntUnaryOperator loudly) {
    return loudly;
  }
}
