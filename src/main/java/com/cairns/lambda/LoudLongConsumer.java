package com.cairns.lambda;

import java.util.function.LongConsumer;

/**
 * Loud version of the {@link LongConsumer} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudLongConsumer)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive long to/from {@link Long}.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
@FunctionalInterface
public interface LoudLongConsumer extends LongConsumer {
  /**
   * Default implementation will call {@link #acceptLoudly(long)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default void accept(long value) {
    LoudRunnable.of(() -> acceptLoudly(value)).run();
  }

  /**
   * See {@link LongConsumer#accept(long)}, but with the ability to throw an {@link Exception}.
   */
  void acceptLoudly(long value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudLongConsumer of(LoudLongConsumer loudly) {
    return loudly;
  }
}
