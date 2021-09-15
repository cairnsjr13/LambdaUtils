package com.cairns.lambda;

import java.util.function.UnaryOperator;

/**
 * Loud version of the {@link UnaryOperator} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudUnaryOperator)} method to simplify creation with lambdas.
 *
 * @author cairnsjr13 - created 14/Sep/2021
 *
 * @param <T> the type of the operand and result of the operator
 *
 * @see LoudFunction
 */
@FunctionalInterface
public interface LoudUnaryOperator<T> extends LoudFunction<T, T>, UnaryOperator<T> {
  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T> LoudUnaryOperator<T> of(LoudUnaryOperator<T> loudly) {
    return loudly;
  }
}
