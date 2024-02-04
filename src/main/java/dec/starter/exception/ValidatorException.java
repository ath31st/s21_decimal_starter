package dec.starter.exception;

/**
 * Custom exception class for validation errors.
 */
public class ValidatorException extends RuntimeException {
  public ValidatorException(String message) {
    super(message);
  }
}
