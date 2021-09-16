package com.cairns.lambda;

import java.util.function.ObjDoubleConsumer;

/**
 * Loud version of the {@link ObjDoubleConsumer} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudObjDoubleConsumer)} method to simplify instance creation with lambdas.
 *
 * @apiNote This avoids autoboxing issues with primitive long to/from {@link Long}.
 *
 * @author cairnsjr13 - created 15/Sep/2021
 *
 * @param <T> the type of the object argument to the operation
 */
@FunctionalInterface
public interface LoudObjDoubleConsumer<T> extends ObjDoubleConsumer<T> {
  /**
   * Default implementation will call {@link #acceptLoudly(Object, double)}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default void accept(T t, double value) {
    LoudRunnable.of(() -> acceptLoudly(t, value)).run();
  }

  /**
   * See {@link ObjDoubleConsumer#accept(Object, double)}, but with the ability to throw an {@link Exception}.
   */
  void acceptLoudly(T t, double value) throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T> LoudObjDoubleConsumer<T> of(LoudObjDoubleConsumer<T> loudly) {
    return loudly;
  }
}
