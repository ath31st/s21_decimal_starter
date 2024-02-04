package dec.starter.exception;

/**
 * Custom exception class for throwing errors from TestBuilder.
 */
public class TestBuilderException extends RuntimeException {
  public TestBuilderException(String message) {
    super(message);
  }
}
