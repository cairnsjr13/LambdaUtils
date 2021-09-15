package com.cairns.lambda;

import java.util.function.Consumer;

/**
 * Loud version of the {@link Consumer} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudConsumer)} method to simplify creation with lambdas.
 *
 * @author cairnsjr13 - 14/Sep/2021
 *
 * @param <T> the type of the input to the operation
 */
@FunctionalInterface
public interface LoudConsumer<T> extends Consumer<T> {
  /**
   * Default implementation will return {@link #acceptLoudly(Object)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default void accept(T t) {
    LoudRunnable.of(() -> acceptLoudly(t)).run();
  }

  /**
   * See {@link Consumer#accept(Object)}, but with the ability to throw an {@link Exception}.
   */
  void acceptLoudly(T t) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T> LoudConsumer<T> of(LoudConsumer<T> loudly) {
    return loudly;
  }
}
