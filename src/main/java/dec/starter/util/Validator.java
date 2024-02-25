package dec.starter.util;

import static dec.starter.constant.ArithmeticConstants.MAX_PRECISION;
import static dec.starter.constant.ArithmeticConstants.MAX_SCALE;
import static dec.starter.constant.StringConstants.REGEXP_HEX_INT;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Validator class provides methods to validate properties of BigDecimal numbers.
 * It checks for conditions such as scale, precision, and range to determine the validity
 * of a given BigDecimal number based on predefined constants and thresholds.
 */
public class Validator {
  private final BigDecimal maxS21Decimal = new BigDecimal("79228162514264337593543950335");
  private final BigDecimal minS21Decimal = new BigDecimal("-79228162514264337593543950335");

  /**
   * Checks the validity of a BigDecimal number based on scale, precision, and range.
   *
   * @param bd The BigDecimal number to be validated.
   * @return Zero if the BigDecimal is valid,
   *     1 if the number is too large or equal to positive infinity,
   *     2 if the number is too small or equal to negative infinity.
   */
  public int checkBigDecimal(BigDecimal bd) {
    int checkRes = 0;
    int scale = bd.scale();
    int precision = bd.precision();

    if (scale > MAX_SCALE.getValue() || bd.compareTo(minS21Decimal) < 0) {
      checkRes = 2;
    } else if (precision > MAX_PRECISION.getValue() || bd.compareTo(maxS21Decimal) > 0) {
      checkRes = 1;
    } else if ((new BigDecimal(bd.unscaledValue()).compareTo(maxS21Decimal)) > 0) {
      checkRes = 3;
    }
    return checkRes;
  }

  /**
   * Checks the validity of a string representation for an S21Decimal value.
   * The string should be in the format of four hexadecimal integers separated by commas,
   * representing the lowBits, midBits, highBits, and signScaleBits, respectively.
   *
   * @param strVal The string representation of an S21Decimal value.
   * @throws IllegalArgumentException If the input string does not match the expected format.
   */
  public void checkStrS21decimal(String strVal) {
    Pattern pattern = Pattern.compile(REGEXP_HEX_INT.getValue());
    Matcher matcher = pattern.matcher(strVal);

    int i = 0;
    while (matcher.find()) {
      i++;
    }

    if (i != 4) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Checks if the provided signScaleBits meet the validity criteria.
   *
   * @param signScaleBits The signScaleBits value to be checked.
   * @throws IllegalArgumentException If the signScaleBits are invalid.
   */
  public void checkSignScaleBits(int signScaleBits) {
    if ((signScaleBits & 0xFFFF) != 0 || (signScaleBits >> 24 & 0x7F) != 0) {
      throw new IllegalArgumentException("Invalid signScaleBits: Unused bits must be zero.");
    }

    if (((signScaleBits >> 16) & 0xFF) > 28) {
      throw new IllegalArgumentException("Invalid signScaleBits: Exponent value exceeds 28.");
    }
  }
}
