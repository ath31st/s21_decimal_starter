package dec.gen;

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

  public void setSignInBit(int sign) {
    if (sign == 1 || sign == 0) {
      signScaleBits &= 0x7FFFFFFF;
      signScaleBits |= (sign & 1) << 31;
    }
  }

  public void setScaleInBit(int scale) {
    if (scale >= 0 && scale <= 28) {
      signScaleBits &= ~(0xFF << 16);
      signScaleBits |= scale << 16;
    }
  }

  @Override
  public String toString() {
    return "s21_decimal = {{0x"
        + Integer.toHexString(lowBits)
        + ", 0x" + Integer.toHexString(midBits)
        + ", 0x" + Integer.toHexString(highBits)
        + ", 0x" + Integer.toHexString(signScaleBits) + "}}";
  }

  public String extendToString(String name) {
    return String.format("s21_decimal dec_%s = {{0x%s, 0x%s, 0x%s, 0x%s}};",
        name,
        Integer.toHexString(lowBits),
        Integer.toHexString(midBits),
        Integer.toHexString(highBits),
        Integer.toHexString(signScaleBits));
  }
}
