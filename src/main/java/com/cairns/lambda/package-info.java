/**
 * This package provides helper interfaces that make working with lambdas and checked
 * exceptions a lot less verbose and boilerplate heavy.  All checked exceptions are caught
 * and rethrown after being wrapped in an {@link com.cairns.lambda.UncaughtCheckedException}.
 *
 * <p/>
 * <b>Let's imagine we want to print out all of the lines from a list of paths.</b>
 * <p />
 *
 * With standard, verbose lambda, we would do this:
 * <pre><code>
 * List<Path> paths = ...;
 * paths.stream().map((path) -> {
 *   try {
 *     return Files.readAllLines(path);
 *   }
 *   catch (IOException e) {
 *     throw new RuntimeException(e);
 *   }
 * }).forEach(System.out::println);
 * </code></pre>
 *
 * <p />
 *
 * With the lambda utils in this package, we would do this:
 * <pre><code>
 * List<Path> paths = ...;
 * paths.stream().map(LoudFunction.of(Files::readAllLines)).forEach(System.out::println);
 * </code></pre>
 *
 * @author cairnsjr13 - created 13/Sep/2021
 */
package com.cairns.lambda;
