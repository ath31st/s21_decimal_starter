package dec.gen;

import static dec.gen.Constants.MAX_BIT_LENGTH;
import static dec.gen.Constants.MAX_SCALE;

import java.math.BigDecimal;

public class Validator {
  public boolean checkDecimalString(String decStr) {
    boolean checkRes = true;
    try {
      BigDecimal bd = new BigDecimal(decStr);
      if (bd.scale() > MAX_SCALE.getValue()
          || bd.unscaledValue().bitLength() > MAX_BIT_LENGTH.getValue()) {
        checkRes = false;
      }
    } catch (Exception e) {
      checkRes = false;
    }
    return checkRes;
  }
}
