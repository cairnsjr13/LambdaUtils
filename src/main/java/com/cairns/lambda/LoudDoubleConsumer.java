package com.cairns.lambda;

import java.util.function.DoubleConsumer;

/**
 * Loud version of the {@link DoubleConsumer} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudDoubleConsumer)} method to simplify creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive double to/from {@link Double}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
@FunctionalInterface
public interface LoudDoubleConsumer extends DoubleConsumer {
  /**
   * Default implementation will return {@link #acceptLoudly(double)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default void accept(double value) {
    LoudRunnable.of(() -> acceptLoudly(value)).run();
  }

  /**
   * See {@link DoubleConsumer#accept(double)}, but with the ability to throw an {@link Exception}.
   */
  void acceptLoudly(double value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudDoubleConsumer of(LoudDoubleConsumer loudly) {
    return loudly;
  }
}
