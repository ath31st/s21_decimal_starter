package dec.starter.util;

import static dec.starter.constant.ArithmeticConstants.MAX_BIT_LENGTH;
import static dec.starter.constant.ArithmeticConstants.MAX_SCALE;
import static dec.starter.constant.StringConstants.OVERFLOW_VALUES;
import static dec.starter.constant.StringConstants.WRONG_INPUT;

import dec.starter.exception.ValidatorException;
import java.math.BigDecimal;

public class Validator {
  private final BigDecimal maxS21Decimal = new BigDecimal("79228162514264337593543950335");
  private final BigDecimal minS21Decimal = new BigDecimal("-79228162514264337593543950335");

  public void checkDecimalString(String decStr) {
    try {
      BigDecimal bd = new BigDecimal(decStr);
      if (bd.scale() > 29 || bd.compareTo(maxS21Decimal) > 0 || bd.compareTo(minS21Decimal) < 0) {
        throw new ValidatorException(String.format(OVERFLOW_VALUES.getValue(),
            MAX_BIT_LENGTH.getValue(), MAX_SCALE.getValue()));
      }
//      if (bd.scale() > MAX_SCALE.getValue()
//          || bd.unscaledValue().bitLength() > MAX_BIT_LENGTH.getValue()) {
//        throw new ValidatorException(String.format(OVERFLOW_VALUES.getValue(),
//            MAX_BIT_LENGTH.getValue(), MAX_SCALE.getValue()));
//      }
    } catch (ValidatorException e) {
      throw new ValidatorException(e.getMessage());
    } catch (Exception e) {
      throw new ValidatorException(WRONG_INPUT.getValue());
    }
  }

  //  0 - OK
  //  1 - the number is too large or equal to infinity
  //  2 - the number is too small or equal to negative infinity
  public int checkBigDecimal(BigDecimal bd) {
    int checkRes = 0;
    int scale = bd.scale();
    if (scale > MAX_SCALE.getValue()) {
      checkRes = 2;
    } else if (scale == -1) {
      checkRes = 1;
    }
    if (checkRes == 0 && (bd.unscaledValue().bitLength() > MAX_BIT_LENGTH.getValue())) {
      checkRes = 1;
    }
    return checkRes;
  }
}
