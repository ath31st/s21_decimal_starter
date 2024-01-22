package dec.gen;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Arithmetic {

  public void add(BigDecimal bd1, BigDecimal bd2) {
    System.out.println(bd1.add(bd2).toPlainString());
  }

  public void sub(BigDecimal bd1, BigDecimal bd2) {
    System.out.println(bd1.subtract(bd2).toPlainString());
  }

  public void mul(BigDecimal bd1, BigDecimal bd2) {
    System.out.println(bd1.multiply(bd2).toPlainString());
  }

  public void div(BigDecimal bd1, BigDecimal bd2) {
    if (bd2.compareTo(BigDecimal.ZERO) != 0) {
      BigDecimal result = bd1.divide(bd2, Constants.MAX_SCALE.getValue(), RoundingMode.HALF_EVEN);
      System.out.println(result.toPlainString());
    } else {
      System.out.println("Деление на ноль невозможно.");
    }
  }
}
