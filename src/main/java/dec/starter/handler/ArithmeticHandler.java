package dec.starter.handler;

import static dec.starter.constant.ArithmeticConstants.MAX_PRECISION;
import static dec.starter.constant.ArithmeticConstants.MAX_SCALE;
import static dec.starter.constant.StringConstants.ZERO_DIV;

import dec.starter.constant.FunctionNames;
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

  public BigDecimal additionalRounding(BigDecimal bd) {
    if (bd.scale() == MAX_SCALE.getValue() + 1
        && bd.abs().compareTo(BigDecimal.ZERO) > 0 && bd.abs().compareTo(BigDecimal.ONE) < 0) {
      return bd.round(new MathContext(MAX_PRECISION.getValue() - 1, RoundingMode.HALF_EVEN));
    }
    return bd;
  }

  public BigDecimal calculateResult(BigDecimal bd1, BigDecimal bd2, int action) {
    BigDecimal res = BigDecimal.ZERO;
    switch (action) {
      case 1:
        res = add(bd1, bd2);
        break;
      case 2:
        res = sub(bd1, bd2);
        break;
      case 3:
        res = mul(bd1, bd2);
        break;
      case 4:
        res = div(bd1, bd2);
        break;
      default:
        break;
    }
    return res;
  }

  public BigDecimal calculateResult(BigDecimal bd1, BigDecimal bd2, FunctionNames fName) {
    BigDecimal res = BigDecimal.ZERO;
    switch (fName) {
      case S21_ADD:
        res = add(bd1, bd2);
        break;
      case S21_SUB:
        res = sub(bd1, bd2);
        break;
      case S21_MUL:
        res = mul(bd1, bd2);
        break;
      case S21_DIV:
        res = div(bd1, bd2);
        break;
      default:
        break;
    }
    return res;
  }
}
