package dec.starter.handler;

import static dec.starter.constant.StringConstants.ZERO_DIV;

import dec.starter.constant.ArithmeticConstants;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ArithmeticHandler {

  public BigDecimal add(BigDecimal bd1, BigDecimal bd2) {
    return bd1.add(bd2);
  }

  public BigDecimal sub(BigDecimal bd1, BigDecimal bd2) {
    return bd1.subtract(bd2);
  }

  public BigDecimal mul(BigDecimal bd1, BigDecimal bd2) {
    return bd1.multiply(bd2);
  }

  public BigDecimal div(BigDecimal bd1, BigDecimal bd2) {
    if (bd2.compareTo(BigDecimal.ZERO) != 0) {
      BigDecimal res = bd1
          .divide(bd2, ArithmeticConstants.MAX_SCALE.getValue(), RoundingMode.HALF_EVEN);
      return new BigDecimal(replaceTrailingZeroes(res.toPlainString()));
    } else {
      throw new IllegalArgumentException(ZERO_DIV.getValue());
    }
  }

  private String replaceTrailingZeroes(String plainStrBigDecimal) {
    if (plainStrBigDecimal.contains(".")) {
      return plainStrBigDecimal
          .replaceAll("\\.?(\\d*?)0*$", "$1")
          .replaceAll("\\.$", "");
    }
    return plainStrBigDecimal;
  }
}
