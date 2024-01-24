package dec.starter.util;

import static dec.starter.constant.ArithmeticConstants.MAX_BIT_LENGTH;
import static dec.starter.constant.ArithmeticConstants.MAX_SCALE;
import static dec.starter.constant.StringConstants.OVERFLOW_VALUES;
import static dec.starter.constant.StringConstants.WRONG_INPUT;

import java.math.BigDecimal;

public class Validator {
  public boolean checkDecimalString(String decStr) {
    boolean checkRes = true;
    try {
      BigDecimal bd = new BigDecimal(decStr);
      if (bd.scale() > MAX_SCALE.getValue()
          || bd.unscaledValue().bitLength() > MAX_BIT_LENGTH.getValue()) {
        checkRes = false;
        System.out.printf(OVERFLOW_VALUES.getValue(),
            MAX_BIT_LENGTH.getValue(), MAX_SCALE.getValue());
      }
    } catch (Exception e) {
      checkRes = false;
      System.out.println(WRONG_INPUT.getValue());
    }
    return checkRes;
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
