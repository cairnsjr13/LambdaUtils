package com.cairns.lambda;

/**
 * Wrapper exception for {@link Exception}s that are thrown from loud functional interfaces in an
 * unchecked manner.  {@link RuntimeException}s are guaranteed by the framework to never be wrapped.
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
public final class UncaughtCheckedException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  /**
   * These should only be created by the framework internally.
   */
  UncaughtCheckedException(Exception cause) {
    super(cause);
  }
}
