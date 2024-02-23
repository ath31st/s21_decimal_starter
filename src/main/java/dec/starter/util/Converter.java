package dec.starter.util;

import static dec.starter.constant.ArithmeticConstants.NEG_SIGN;

import dec.starter.model.S21Decimal;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
   * Converts a string representation to a custom 96-bit signed decimal format S21Decimal.
   *
   * @param strValue The string representation of the S21Decimal value.
   * @return The equivalent S21Decimal representation of the input string.
   * @throws IllegalArgumentException If the input string format is incorrect.
   */
  public S21Decimal fromStrToS21Dec(String strValue) {
    Pattern pattern = Pattern.compile("0x[0-9a-fA-F]+");
    Matcher matcher = pattern.matcher(strValue);

    int[] intValues = new int[4];
    int i = 0;
    while (matcher.find() && i < 4) {
      intValues[i++] = Integer.parseUnsignedInt(matcher.group().substring(2), 16);
    }

    if (i != 4) {
      throw new IllegalArgumentException("Invalid S21Decimal string format");
    }

    S21Decimal s21Dec = new S21Decimal();
    s21Dec.setLowBits(intValues[0]);
    s21Dec.setMidBits(intValues[1]);
    s21Dec.setHighBits(intValues[2]);
    s21Dec.setSignScaleBits(intValues[3]);

    return s21Dec;
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

  /**
   * Converts a custom 96-bit signed decimal format S21Decimal to a BigDecimal.
   *
   * @param s21Dec The S21Decimal to be converted.
   * @return The equivalent BigDecimal representation of the input S21Decimal.
   */
  public BigDecimal fromS21DecToDec(S21Decimal s21Dec) {
    int scale = s21Dec.getScaleInBit();
    int sign = s21Dec.getSignInBit() == NEG_SIGN.getValue() ? -1 : 1;

    int[] intValues = {s21Dec.getLowBits(), s21Dec.getMidBits(), s21Dec.getHighBits()};
    BigInteger unscaledValue = combine32BitValues(intValues);

    return new BigDecimal(unscaledValue, scale).multiply(BigDecimal.valueOf(sign));
  }

  /**
   * Combines three 32-bit integer values into a BigInteger.
   *
   * @param intValues An array of three 32-bit integer values.
   * @return The BigInteger representation of the combined input values.
   */
  private BigInteger combine32BitValues(int[] intValues) {
    byte[] bytes = new byte[12];

    for (int i = 0; i < 3; i++) {
      int intValue = intValues[i];

      for (int j = 0; j < 4; j++) {
        bytes[i * 4 + j] = (byte) ((intValue >> (j * 8)) & 0xFF);
      }
    }

    byte[] reversedBytes = new byte[12];
    for (int i = 0; i < 12; i++) {
      reversedBytes[i] = bytes[11 - i];
    }

    return new BigInteger(reversedBytes);
  }

}
