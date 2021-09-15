package com.cairns.lambda;

import java.util.function.BinaryOperator;

/**
 * Loud version of the {@link BinaryOperator} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudBinaryOperator)} method to simplify creation with lambdas.
 *
 * @author cairnsjr13 - created 14/Sep/2021
 *
 * @param <T> the type of the operands and result of the operator
 *
 * @see LoudBiFunction
 */
@FunctionalInterface
public interface LoudBinaryOperator<T> extends LoudBiFunction<T, T, T>, BinaryOperator<T> {
  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T> LoudBinaryOperator<T> of(LoudBinaryOperator<T> loudly) {
    return loudly;
  }
}
