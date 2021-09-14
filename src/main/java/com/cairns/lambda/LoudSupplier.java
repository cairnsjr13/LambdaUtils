package com.cairns.lambda;

import java.util.function.Supplier;

/**
 * Loud version of the {@link Supplier} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudSupplier)} method to simplify creation with lambdas.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 *
 * @param <T> The type of results supplied by this supplier
 */
@FunctionalInterface
public interface LoudSupplier<T> extends Supplier<T> {
  /**
   * Default implementation will return {@link #getLoudly()}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default T get() {
    try {
      return getLoudly();
    }
    catch (RuntimeException e) {
      throw e;
    }
    catch (Exception e) {
      throw new UncaughtCheckedException(e);
    }
  }

  /**
   * See {@link Supplier#get()}, but with the ability to throw an {@link Exception}.
   */
  T getLoudly() throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static <T> LoudSupplier<T> of(LoudSupplier<T> loudly) {
    return loudly;
  }
}
