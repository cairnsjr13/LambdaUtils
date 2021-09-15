package com.cairns.lambda;

import java.util.function.IntConsumer;

/**
 * Loud version of {@link IntConsumer} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudIntConsumer)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive int to/from {@link Integer}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 */
@FunctionalInterface
public interface LoudIntConsumer extends IntConsumer {
  /**
   * Default implementation will call {@link #acceptLoudly(int)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default void accept(int value) {
    LoudRunnable.of(() -> acceptLoudly(value)).run();
  }

  /**
   * See {@link IntConsumer#accept(int)}, but with the ability to throw an {@link Exception}.
   */
  void acceptLoudly(int value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudIntConsumer of(LoudIntConsumer loudly) {
    return loudly;
  }
}
