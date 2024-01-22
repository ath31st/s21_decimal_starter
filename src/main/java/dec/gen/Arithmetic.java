package dec.gen;

import java.math.BigDecimal;

public class Arithmetic {

  public void add(BigDecimal bd1, BigDecimal bd2) {
    System.out.println(bd1.add(bd2).toPlainString());
  }
}
