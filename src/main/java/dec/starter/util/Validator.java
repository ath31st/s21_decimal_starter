package dec.starter.util;

import static dec.starter.constant.ArithmeticConstants.MAX_BIT_LENGTH;
import static dec.starter.constant.ArithmeticConstants.MAX_SCALE;
import static dec.starter.constant.StringConstants.OVERFLOW_VALUES;
import static dec.starter.constant.StringConstants.WRONG_INPUT;

import dec.starter.exception.ValidatorException;
import java.math.BigDecimal;

public class Validator {
  public void checkDecimalString(String decStr) {
    try {
      BigDecimal bd = new BigDecimal(decStr);
      if (bd.scale() > MAX_SCALE.getValue()
          || bd.unscaledValue().bitLength() > MAX_BIT_LENGTH.getValue()) {
        throw new ValidatorException(String.format(OVERFLOW_VALUES.getValue(),
            MAX_BIT_LENGTH.getValue(), MAX_SCALE.getValue()));
      }
    } catch (Exception e) {
      throw new ValidatorException(WRONG_INPUT.getValue());
    }
  }

  //  0 - OK
  //  1 - the number is too large or equal to infinity
  //  2 - the number is too small or equal to negative infinity
  public int checkBigDecimal(BigDecimal bd) {
    int checkRes = 0;
    if (bd.scale() > MAX_SCALE.getValue()) {
      checkRes = 2;
    }
    if (checkRes == 0 && bd.unscaledValue().bitLength() > MAX_BIT_LENGTH.getValue()) {
      checkRes = 1;
    }
    return checkRes;
  }
}
