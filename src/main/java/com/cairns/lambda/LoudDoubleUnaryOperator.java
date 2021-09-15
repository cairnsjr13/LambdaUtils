package com.cairns.lambda;

import java.util.function.DoubleUnaryOperator;

/**
 * Loud version of the  interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudDoubleUnaryOperator)} method to simplify creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive double to/from {@link Double}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
@FunctionalInterface
public interface LoudDoubleUnaryOperator extends DoubleUnaryOperator {
  /**
   * Default implementation will return {@link #applyAsDoubleLoudly(double)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default double applyAsDouble(double operand) {
    return LoudDoubleSupplier.of(() -> applyAsDoubleLoudly(operand)).getAsDouble();
  }

  /**
   * See {@link DoubleUnaryOperator#applyAsDouble(double)}, but with the ability to throw an {@link Exception}.
   */
  double applyAsDoubleLoudly(double operand) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudDoubleUnaryOperator of(LoudDoubleUnaryOperator loudly) {
    return loudly;
  }
}
