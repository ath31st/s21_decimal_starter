package dec.gen;

import java.math.BigDecimal;

public class OutputManager {
  public void consolePrint(String str) {
    System.out.println(str);
  }

  public void consolePrintBigDecAndS21Dec(BigDecimal bd, S21Decimal d) {
    System.out.println("Десятичное значение: " + bd.toPlainString());
    System.out.println(d.extendToString("res"));
  }
}
