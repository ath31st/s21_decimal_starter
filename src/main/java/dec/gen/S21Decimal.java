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
    return "S21Decimal{" +
        "lowBits=" + lowBits +
        ", midBits=" + midBits +
        ", highBits=" + highBits +
        ", signScaleBits=" + signScaleBits +
        '}';
  }
}
