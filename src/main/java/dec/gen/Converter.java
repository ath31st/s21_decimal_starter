package dec.gen;

import java.math.BigDecimal;

public class Converter {
  public BigDecimal fromStrToDec(String strValue) {
    return new BigDecimal(strValue);
  }

  public String fromDecToStr(BigDecimal bd) {
    return bd.toPlainString();
  }
}
