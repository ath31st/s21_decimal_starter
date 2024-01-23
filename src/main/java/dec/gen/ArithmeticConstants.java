package dec.gen;

public enum ArithmeticConstants {
  MAX_BIT_LENGTH(96),
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
