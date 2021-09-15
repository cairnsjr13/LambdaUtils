package com.cairns.lambda;

import java.util.function.DoubleBinaryOperator;

/**
 * Loud version of the {@link DoubleBinaryOperator} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudDoubleBinaryOperator)} method to simplify creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive double to/from {@link Double}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
@FunctionalInterface
public interface LoudDoubleBinaryOperator extends DoubleBinaryOperator {
  /**
   * Default implementation will return {@link #applyAsDoubleLoudly(double, double)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default double applyAsDouble(double left, double right) {
    return LoudDoubleSupplier.of(() -> applyAsDoubleLoudly(left, right)).getAsDouble();
  }

  /**
   * See {@link DoubleBinaryOperator#applyAsDouble(double, double)}, but with the ability to throw an {@link Exception}.
   */
  double applyAsDoubleLoudly(double left, double right) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudDoubleBinaryOperator of(LoudDoubleBinaryOperator loudly) {
    return loudly;
  }
}
