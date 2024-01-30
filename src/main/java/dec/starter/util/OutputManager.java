package dec.starter.util;

import static dec.starter.constant.S21DecimalNames.DEC_RES;
import static dec.starter.constant.StringConstants.DECIMAL_VALUE;
import static dec.starter.constant.StringConstants.RES_TOO_LARGE_OR_POS_INF;
import static dec.starter.constant.StringConstants.RES_TOO_SMALL_OR_POS_NEG;

import dec.starter.App;
import dec.starter.model.S21Decimal;
import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OutputManager {
  private static final Logger logger = LogManager.getLogger(OutputManager.class);
  private final Converter converter = new Converter();

  public void consolePrint(String str) {
    System.out.println(str);
  }

  public void consolePrintBigDecAndS21Dec(BigDecimal bd, String dName) {
    S21Decimal d = converter.fromDecToS21Dec(bd);
    System.out.println(DECIMAL_VALUE.getValue() + bd.toPlainString());
    System.out.println(d.extendToString(dName));
  }

  public void handleResultPrinting(BigDecimal res, int check) {
    if (check == 1) {
      consolePrint(RES_TOO_LARGE_OR_POS_INF.getValue());
    } else if (check == 2) {
      consolePrint(RES_TOO_SMALL_OR_POS_NEG.getValue());
    } else {
      consolePrintBigDecAndS21Dec(res, DEC_RES.getValue());
    }
  }
}
