package dec.starter.model;

/**
 * Class representing a custom decimal format for S21_decimal project.
 */
public class S21Decimal {
  private int lowBits;
  private int midBits;
  private int highBits;
  private int signScaleBits;

  public int getLowBits() {
    return lowBits;
  }

  public void setLowBits(int lowBits) {
    this.lowBits = lowBits;
  }

  public int getMidBits() {
    return midBits;
  }

  public void setMidBits(int midBits) {
    this.midBits = midBits;
  }

  public int getHighBits() {
    return highBits;
  }

  public void setHighBits(int highBits) {
    this.highBits = highBits;
  }

  public int getSignScaleBits() {
    return signScaleBits;
  }

  /**
   * Sets the sign bit of the bit[3] s21_decimal value.
   *
   * @param sign The sign bit to be set (0 or 1).
   */
  public void setSignInBit(int sign) {
    if (sign == 1 || sign == 0) {
      signScaleBits &= 0x7FFFFFFF;
      signScaleBits |= (sign & 1) << 31;
    }
  }

  /**
   * Gets the sign bit of the bit[3] S21Decimal value.
   *
   * @return The sign bit (0 or 1).
   */
  public int getSignInBit() {
    return (signScaleBits >>> 31) & 1;
  }

  /**
   * Sets the scale bits of the bit[3] s21_decimal value.
   *
   * @param scale The scale bits to be set (0 to 28).
   */
  public void setScaleInBit(int scale) {
    if (scale >= 0 && scale <= 28) {
      signScaleBits &= ~(0xFF << 16);
      signScaleBits |= scale << 16;
    }
  }

  /**
   * Gets the scale bits of the bit[3] S21Decimal value.
   *
   * @return The scale bits (0 to 28).
   */
  public int getScaleInBit() {
    return (signScaleBits >>> 16) & 0xFF;
  }

  /**
   * Returns a string representation of the s21_decimal.
   *
   * @return The string representation of the s21_decimal.
   */
  @Override
  public String toString() {
    return "s21_decimal = {{0x"
        + Integer.toHexString(lowBits)
        + ", 0x" + Integer.toHexString(midBits)
        + ", 0x" + Integer.toHexString(highBits)
        + ", 0x" + Integer.toHexString(signScaleBits) + "}}";
  }

  /**
   * Returns an extended string representation of the s21_decimal with a given name.
   *
   * @param name The name to be included in the string.
   * @return The extended string representation of the s21_decimal.
   */
  public String extendToString(String name) {
    return String.format("s21_decimal %s = {{0x%s, 0x%s, 0x%s, 0x%s}};",
        name,
        Integer.toHexString(lowBits),
        Integer.toHexString(midBits),
        Integer.toHexString(highBits),
        Integer.toHexString(signScaleBits));
  }
}
