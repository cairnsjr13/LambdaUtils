package com.cairns.lambda;

import java.util.function.ObjLongConsumer;

/**
 * Loud version of the {@link ObjLongConsumer} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudObjLongConsumer)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive long to/from {@link Long}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 *
 * @param <T> the type of the object argument to the operation
 */
@FunctionalInterface
public interface LoudObjLongConsumer<T> extends ObjLongConsumer<T> {
  /**
   * Default implementation will call {@link #acceptLoudly(Object, long)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default void accept(T t, long value) {
    LoudRunnable.of(() -> acceptLoudly(t, value)).run();
  }

  /**
   * See {@link ObjLongConsumer#accept(Object, long)}, but with the ability to throw an {@link Exception}.
   */
  void acceptLoudly(T t, long value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T> LoudObjLongConsumer<T> of(LoudObjLongConsumer<T> loudly) {
    return loudly;
  }
}
