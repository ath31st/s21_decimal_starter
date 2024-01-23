package dec.starter;

import java.math.BigDecimal;

public class OutputManager {
  private final Converter converter = new Converter();

  public void consolePrint(String str) {
    System.out.println(str);
  }

  public void consolePrintBigDecAndS21Dec(BigDecimal bd, String dName) {
    S21Decimal d = converter.fromDecToS21Dec(bd);
    System.out.println("Десятичное значение: " + bd.toPlainString());
    System.out.println(d.extendToString(dName));
  }
}
