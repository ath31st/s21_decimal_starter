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
        System.out.printf("Введенное число превышает объем %d бит или степень %d%n",
            MAX_BIT_LENGTH.getValue(), MAX_SCALE.getValue());
      }
    } catch (Exception e) {
      checkRes = false;
      System.out.println("Некорректный ввод: " + decStr);
    }
    return checkRes;
  }
}
