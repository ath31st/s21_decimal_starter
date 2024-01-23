package dec.gen;

import static dec.gen.ArithmeticConstants.MAX_BIT_LENGTH;
import static dec.gen.ArithmeticConstants.MAX_SCALE;
import static dec.gen.StringConstants.OVERFLOW_VALUES;
import static dec.gen.StringConstants.WRONG_INPUT;

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
}
