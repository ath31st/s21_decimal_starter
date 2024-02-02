package dec.starter.util;

import static dec.starter.constant.ArithmeticConstants.MAX_PRECISION;
import static dec.starter.constant.ArithmeticConstants.MAX_SCALE;

import java.math.BigDecimal;

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
    }
    return checkRes;
  }
}
