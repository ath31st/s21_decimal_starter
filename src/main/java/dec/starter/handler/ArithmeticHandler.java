package dec.starter.handler;

import static dec.starter.constant.ArithmeticConstants.MAX_PRECISION;
import static dec.starter.constant.ArithmeticConstants.MAX_SCALE;
import static dec.starter.constant.StringConstants.ZERO_DIV;

import dec.starter.constant.FunctionNames;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Handler class for arithmetic operations using custom S21Decimal format.
 */
public class ArithmeticHandler {
  private final MathContext mathContextS21Dec
      = new MathContext(MAX_PRECISION.getValue(), RoundingMode.HALF_EVEN);

  /**
   * Performs addition operation on two BigDecimal numbers.
   *
   * @param bd1 The first BigDecimal operand.
   * @param bd2 The second BigDecimal operand.
   * @return The result of the addition operation.
   */

  public BigDecimal add(BigDecimal bd1, BigDecimal bd2) {
    return bd1.add(bd2, mathContextS21Dec);
  }

  /**
   * Performs subtraction operation on two BigDecimal numbers.
   *
   * @param bd1 The first BigDecimal operand.
   * @param bd2 The second BigDecimal operand.
   * @return The result of the subtraction operation.
   */
  public BigDecimal sub(BigDecimal bd1, BigDecimal bd2) {
    return bd1.subtract(bd2, mathContextS21Dec);
  }

  /**
   * Performs multiplication operation on two BigDecimal numbers.
   *
   * @param bd1 The first BigDecimal operand.
   * @param bd2 The second BigDecimal operand.
   * @return The result of the multiplication operation.
   */
  public BigDecimal mul(BigDecimal bd1, BigDecimal bd2) {
    return bd1.multiply(bd2, mathContextS21Dec);
  }

  /**
   * Performs division operation on two BigDecimal numbers.
   *
   * @param bd1 The numerator BigDecimal operand.
   * @param bd2 The denominator BigDecimal operand.
   * @return The result of the division operation.
   * @throws IllegalArgumentException if the denominator is zero.
   */
  public BigDecimal div(BigDecimal bd1, BigDecimal bd2) {
    if (bd2.compareTo(BigDecimal.ZERO) != 0) {
      return bd1.divide(bd2, mathContextS21Dec);
    } else {
      throw new IllegalArgumentException(ZERO_DIV.getValue());
    }
  }

  /**
   * Performs additional rounding for BigDecimal numbers based on specific conditions.
   *
   * @param bd The BigDecimal number to be rounded.
   * @return The result of additional rounding.
   */
  public BigDecimal additionalRounding(BigDecimal bd) {
    if (bd.scale() == MAX_SCALE.getValue() + 1
        && bd.abs().compareTo(BigDecimal.ZERO) > 0 && bd.abs().compareTo(BigDecimal.ONE) < 0) {
      return bd.round(new MathContext(MAX_PRECISION.getValue() - 1, RoundingMode.HALF_EVEN));
    }
    return bd;
  }

  /**
   * Calculates the result of an arithmetic operation based on the specified action code.
   *
   * @param bd1    The first BigDecimal operand.
   * @param bd2    The second BigDecimal operand.
   * @param action The action code representing the arithmetic operation.
   * @return The result of the arithmetic operation.
   */
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

  /**
   * Calculates the result of an arithmetic operation based on the specified function name.
   *
   * @param bd1      The first BigDecimal operand.
   * @param bd2      The second BigDecimal operand.
   * @param funcName The function name representing the arithmetic operation.
   * @return The result of the arithmetic operation.
   */
  public BigDecimal calculateResult(BigDecimal bd1, BigDecimal bd2, FunctionNames funcName) {
    BigDecimal res = BigDecimal.ZERO;
    switch (funcName) {
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
