package com.cairns.lambda;

/**
 * Loud version of the {@link Runnable} interface.
 * All checked exceptions will be wrapped in {@link UncaughtCheckedException}.
 * Use the {@link #of(LoudRunnable)} method to simplify instance creation with lambdas.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
@FunctionalInterface
public interface LoudRunnable extends Runnable {
  /**
   * Default implementation will call {@link #runLoudly()}, rethrow {@link RuntimeException}s,
   * and wrap checked {@link Exception}s with an {@link UncaughtCheckedException}.
   *
   * {@inheritDoc}
   */
  @Override
  default void run() {
    try {
      runLoudly();
    }
    catch (RuntimeException e) {
      throw e;
    }
    catch (Exception e) {
      throw new UncaughtCheckedException(e);
    }
  }

  /**
   * See {@link Runnable#run()}, but with the ability to throw an {@link Exception}.
   */
  void runLoudly() throws Exception;

  /**
   * Syntactic sugar method to make creation with lambdas simple.
   */
  static LoudRunnable of(LoudRunnable loudly) {
    return loudly;
  }
}
