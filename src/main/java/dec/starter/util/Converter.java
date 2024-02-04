package dec.starter.util;

import static dec.starter.constant.ArithmeticConstants.NEG_SIGN;

import dec.starter.model.S21Decimal;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * The Converter class provides utility methods for converting
 * between different numeric representations.
 * It supports the conversion
 * of decimal values from standard String representations to
 * BigDecimal, and the conversion of BigDecimal to a
 * custom 32-bit signed decimal format S21Decimal.
 * <p>
 * This class is designed to facilitate seamless conversion between
 * common numeric types in a standardized way.
 * </p>
 */
public class Converter {
  /**
   * Converts a string representation to a BigDecimal.
   *
   * @param strValue The string representation of the decimal value.
   * @return The equivalent BigDecimal representation of the input string.
   */
  public BigDecimal fromStrToDec(String strValue) {
    return new BigDecimal(strValue);
  }

  /**
   * Converts a BigDecimal to a custom 96-bit signed decimal format S21Decimal.
   *
   * @param bd The BigDecimal to be converted.
   * @return The equivalent S21Decimal representation of the input BigDecimal.
   */
  public S21Decimal fromDecToS21Dec(BigDecimal bd) {
    S21Decimal d = new S21Decimal();

    BigInteger unscaledValue = bd
        .unscaledValue()
        .abs();

    d.setScaleInBit(bd.scale());
    if (bd.signum() == -1) {
      d.setSignInBit(NEG_SIGN.getValue());
    }

    int[] intValues = splitTo32BitValues(unscaledValue);

    d.setLowBits(intValues[0]);
    d.setMidBits(intValues[1]);
    d.setHighBits(intValues[2]);

    return d;
  }

  /**
   * Splits a BigInteger value into three 32-bit integer values
   * for representation in a custom 32-bit signed decimal format.
   *
   * @param value The BigInteger value to be split.
   * @return An array of three 32-bit integer values representing the input BigInteger.
   */
  private int[] splitTo32BitValues(BigInteger value) {
    int[] intValues = new int[3];

    byte[] bytes = value.toByteArray();
    int length = bytes.length;

    for (int i = 0; i < 3; i++) {
      int offset = i * 4;
      int intValue = 0;

      for (int j = 0; j < 4; j++) {
        if (offset + j < length) {
          intValue |= (bytes[length - offset - j - 1] & 0xFF) << (j * 8);
        }
      }

      intValues[i] = intValue;
    }

    return intValues;
  }
}
