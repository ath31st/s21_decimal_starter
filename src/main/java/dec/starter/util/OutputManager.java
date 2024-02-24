package dec.starter.util;

import static dec.starter.constant.S21DecimalNames.DEC_RES;
import static dec.starter.constant.StringConstants.DECIMAL_VALUE;
import static dec.starter.constant.StringConstants.DOT_SEP;
import static dec.starter.constant.StringConstants.RES_NO_FIT_IN_MANTISSA;
import static dec.starter.constant.StringConstants.RES_TOO_LARGE_OR_POS_INF;
import static dec.starter.constant.StringConstants.RES_TOO_SMALL_OR_POS_NEG;

import dec.starter.model.S21Decimal;
import java.math.BigDecimal;

/**
 * Manages the output of the application,
 * including printing to the console and handling result printing.
 */
public class OutputManager {
  private final Converter converter = new Converter();

  /**
   * Prints the given string to the console.
   *
   * @param str The string to be printed to the console.
   */
  public void consolePrint(String str) {
    System.out.println(str);
  }

  /**
   * Prints the given BigDecimal and its corresponding S21Decimal to the console.
   *
   * @param bd          The BigDecimal value.
   * @param decimalName The name of the S21Decimal.
   */
  public void consolePrintBigDecAndS21Dec(BigDecimal bd, String decimalName) {
    S21Decimal d = converter.fromDecToS21Dec(bd);
    consolePrint(DOT_SEP.getValue());
    consolePrint(DECIMAL_VALUE.getValue() + bd.toPlainString());
    consolePrint(d.extendToString(decimalName));
  }

  /**
   * Handles the printing of a result based on the check value.
   *
   * @param res   The result BigDecimal value.
   * @param check The check value indicating the result state.
   */
  public void handleResultPrinting(BigDecimal res, int check) {
    if (check == 1) {
      consolePrint(RES_TOO_LARGE_OR_POS_INF.getValue());
    } else if (check == 2) {
      consolePrint(RES_TOO_SMALL_OR_POS_NEG.getValue());
    } else if (check == 3) {
      consolePrint(RES_NO_FIT_IN_MANTISSA.getValue());
    } else {
      consolePrintBigDecAndS21Dec(res, DEC_RES.getValue());
    }
  }

  public void printDecimalValueFromBigDecimal(BigDecimal bd) {
    consolePrint(DECIMAL_VALUE.getValue() + System.lineSeparator() + bd.toPlainString());
  }
}
