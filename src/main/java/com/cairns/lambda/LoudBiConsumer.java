package com.cairns.lambda;

import java.util.function.BiConsumer;

/**
 * Loud version of the {@link BiConsumer} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudBiConsumer)} method to simplify creation with lambdas.
 *
 * @author cairnsjr13 - created 14/Sep/2021
 *
 * @param <T> the type of the first argument to the operation
 * @param <U> the type of the second argument to the operation
 */
@FunctionalInterface
public interface LoudBiConsumer<T, U> extends BiConsumer<T, U> {
  /**
   * Default implementation will return {@link #acceptLoudly(Object, Object)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default void accept(T t, U u) {
    LoudRunnable.of(() -> acceptLoudly(t, u)).run();
  }

  /**
   * See {@link BiConsumer#accept(Object, Object)}, but with the ability to throw an {@link Exception}.
   */
  void acceptLoudly(T t, U u) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T, U> LoudBiConsumer<T, U> of(LoudBiConsumer<T, U> loudly) {
    return loudly;
  }
}
