package com.cairns.lambda;

import java.util.function.ToDoubleBiFunction;

/**
 * Loud version of the {@link ToDoubleBiFunction} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudToDoubleBiFunction)} method to simplify creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive double to/from {@link Double}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 *
 * @param <T> the type of the first argument to the function
 * @param <U> the type of the second argument to the function
 */
@FunctionalInterface
public interface LoudToDoubleBiFunction<T, U> extends ToDoubleBiFunction<T, U> {
  /**
   * Default implementation will return {@link #applyAsDoubleLoudly(Object, Object)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default double applyAsDouble(T t, U u) {
    return LoudDoubleSupplier.of(() -> applyAsDoubleLoudly(t, u)).getAsDouble();
  }

  /**
   * See {@link ToDoubleBiFunction#applyAsDouble(Object, Object)}, but with the ability to throw an {@link Exception}.
   */
  double applyAsDoubleLoudly(T t, U u) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T, U> LoudToDoubleBiFunction<T, U> of(LoudToDoubleBiFunction<T, U> loudly) {
    return loudly;
  }
}
