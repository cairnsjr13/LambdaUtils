package com.cairns.lambda;

import java.util.function.LongUnaryOperator;

/**
 * Loud version of the {@link LongUnaryOperator} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudLongUnaryOperator)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive long to/from {@link Long}.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
@FunctionalInterface
public interface LoudLongUnaryOperator extends LongUnaryOperator {
  /**
   * Default implementation will call {@link #applyAsLongLoudly(long)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default long applyAsLong(long operand) {
    return LoudLongSupplier.of(() -> applyAsLongLoudly(operand)).getAsLong();
  }

  /**
   * See {@link LongUnaryOperator#applyAsLong(long)} , but with the ability to throw an {@link Exception}.
   */
  long applyAsLongLoudly(long operand) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudLongUnaryOperator of(LoudLongUnaryOperator loudly) {
    return loudly;
  }
}
