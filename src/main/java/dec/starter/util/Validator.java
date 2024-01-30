package dec.starter.util;

import static dec.starter.constant.ArithmeticConstants.MAX_PRECISION;
import static dec.starter.constant.ArithmeticConstants.MAX_SCALE;

import java.math.BigDecimal;

public class Validator {
  private final BigDecimal maxS21Decimal = new BigDecimal("79228162514264337593543950335");
  private final BigDecimal minS21Decimal = new BigDecimal("-79228162514264337593543950335");

  //  0 - OK
  //  1 - the number is too large or equal to infinity
  //  2 - the number is too small or equal to negative infinity
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
