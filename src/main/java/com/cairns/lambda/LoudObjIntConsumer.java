package com.cairns.lambda;

import java.util.function.ObjIntConsumer;

/**
 * Loud version of the {@link ObjIntConsumer} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudObjIntConsumer)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive long to/from {@link Long}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 *
 * @param <T> the type of the object argument to the operation
 */
@FunctionalInterface
public interface LoudObjIntConsumer<T> extends ObjIntConsumer<T> {
  /**
   * Default implementation will call {@link #acceptLoudly(Object, int)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default void accept(T t, int value) {
    LoudRunnable.of(() -> acceptLoudly(t, value)).run();
  }

  /**
   * See {@link ObjIntConsumer#accept(Object, int)}, but with the ability to throw an {@link Exception}.
   */
  void acceptLoudly(T t, int value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T> LoudObjIntConsumer<T> of(LoudObjIntConsumer<T> loudly) {
    return loudly;
  }
}
