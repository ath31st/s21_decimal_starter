package dec.starter.constant;

public enum ArithmeticConstants {
  //79228162514264337593543950335
  MAX_BIT_LENGTH(96),
  MAX_PRECISION(28),
  MAX_SCALE(28),
  NEG_SIGN(1),
  POS_SIGN(0);

  private final Integer value;

  ArithmeticConstants(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }
}
