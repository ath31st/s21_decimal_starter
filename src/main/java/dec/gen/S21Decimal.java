package dec.gen;

public class S21Decimal {
  private int lowBit;
  private int midBit;
  private int highBit;
  private int signScaleBit;

  public int getLowBit() {
    return lowBit;
  }

  public void setLowBit(int lowBit) {
    this.lowBit = lowBit;
  }

  public int getMidBit() {
    return midBit;
  }

  public void setMidBit(int midBit) {
    this.midBit = midBit;
  }

  public int getHighBit() {
    return highBit;
  }

  public void setHighBit(int highBit) {
    this.highBit = highBit;
  }

  public int getSignScaleBit() {
    return signScaleBit;
  }

  public void setSignInBit(int sign) {
    if (sign == 1 || sign == 0) {
      signScaleBit &= 0x7FFFFFFF;
      signScaleBit |= (sign & 1) << 31;
    }
  }

  public void setScaleInBit(int scale) {
    if (scale >= 0 && scale <= 28) {
      signScaleBit &= ~(0xFF << 16);
      signScaleBit |= scale << 16;
    }
  }
}
