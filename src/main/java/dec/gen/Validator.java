package dec.gen;

import java.math.BigDecimal;

public class Validator {
  private static int MAX_BIT_LENGTH = 96;
  private static int MAX_SCALE = 28;

  public boolean checkDecimalString(String decStr) {
    boolean checkRes = true;
    try {
      BigDecimal bd = new BigDecimal(decStr);
      if (bd.scale() > MAX_SCALE || bd.unscaledValue().bitLength() > MAX_BIT_LENGTH) {
        checkRes = false;
      }
    } catch (Exception e) {
      checkRes = false;
    }
    return checkRes;
  }
}
