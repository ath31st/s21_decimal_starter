package dec.starter.handler;

import static dec.starter.constant.ArithmeticConstants.MAX_PRECISION;
import static dec.starter.constant.StringConstants.ZERO_DIV;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ArithmeticHandler {
  private final MathContext mathContextS21Dec
      = new MathContext(MAX_PRECISION.getValue(), RoundingMode.HALF_EVEN);

  public BigDecimal add(BigDecimal bd1, BigDecimal bd2) {
    return bd1.add(bd2, mathContextS21Dec);
  }

  public BigDecimal sub(BigDecimal bd1, BigDecimal bd2) {
    return bd1.subtract(bd2, mathContextS21Dec);
  }

  public BigDecimal mul(BigDecimal bd1, BigDecimal bd2) {
    return bd1.multiply(bd2, mathContextS21Dec);
  }

  public BigDecimal div(BigDecimal bd1, BigDecimal bd2) {
    if (bd2.compareTo(BigDecimal.ZERO) != 0) {
      return bd1.divide(bd2, mathContextS21Dec);
    } else {
      throw new IllegalArgumentException(ZERO_DIV.getValue());
    }
  }
}
