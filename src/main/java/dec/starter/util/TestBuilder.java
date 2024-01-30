package dec.starter.util;

import static dec.starter.constant.S21DecimalNames.DEC_1;
import static dec.starter.constant.S21DecimalNames.DEC_2;
import static dec.starter.constant.S21DecimalNames.DEC_CHECK;
import static dec.starter.constant.TestStringConstants.DONT_FORGET_INCLUDE;
import static dec.starter.constant.TestStringConstants.TEST_CASE_NAME_TEMPLATE;
import static dec.starter.constant.TestStringConstants.TEST_SUITE_TEMPLATE;
import static dec.starter.constant.TestStringConstants.TEST_TEMPLATE;

import dec.starter.constant.FunctionNames;
import dec.starter.handler.ArithmeticHandler;
import dec.starter.model.S21Decimal;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.lang3.tuple.Triple;

public class TestBuilder {
  private final Validator validator;
  private final Converter converter;
  private final ArithmeticHandler arithmeticHandler;

  public TestBuilder(Validator validator, Converter converter, ArithmeticHandler arithmeticHandler) {
    this.validator = validator;
    this.converter = converter;
    this.arithmeticHandler = arithmeticHandler;
  }

  public String buildAllSuitsAtOnce(int count) {
    return buildTestSuitCommon(
        FunctionNames.ALL_FUNCTIONS,
        count,
        Arrays.stream(FunctionNames.values())
            .filter(f -> f != FunctionNames.ALL_FUNCTIONS)
            .collect(Collectors.toList()));
  }

  public String buildTestSuit(FunctionNames fName, int count) {
    return buildTestSuitCommon(fName, count, Collections.singletonList(fName));
  }

  private String buildTestSuitCommon(FunctionNames footerFName, int count, List<FunctionNames> functionList) {
    StringBuilder sb = new StringBuilder();
    sb.append(testHeader()).append(System.lineSeparator());

    for (FunctionNames currentFName : functionList) {
      for (int i = 0; i < count; i++) {
        Triple<BigDecimal, BigDecimal, BigDecimal> t = generateOkValues(currentFName);
        sb.append(testTemplate(currentFName, i, t.getLeft(), t.getMiddle(), t.getRight()));
        sb.append(System.lineSeparator());
      }
    }

    String commonTCaseNames = functionList.stream()
        .filter(f -> f != FunctionNames.ALL_FUNCTIONS)
        .map(f -> tCaseNamesForFooter(f, count))
        .collect(Collectors.joining(System.lineSeparator()));

    sb.append(footerTestModule(footerFName, commonTCaseNames));

    return sb.toString();
  }


  private Triple<BigDecimal, BigDecimal, BigDecimal> generateOkValues(FunctionNames fName) {
    boolean genTry = true;
    BigDecimal bd1 = BigDecimal.ZERO;
    BigDecimal bd2 = BigDecimal.ZERO;
    BigDecimal res = BigDecimal.ZERO;

    while (genTry) {
      bd1 = BigDecimalGenerator.generateLimitedBigDecimal();
      bd2 = BigDecimalGenerator.generateLimitedBigDecimal();

      switch (fName) {
        case S21_ADD:
          res = arithmeticHandler.add(bd1, bd2);
          break;
        case S21_SUB:
          res = arithmeticHandler.sub(bd1, bd2);
          break;
        case S21_MUL:
          res = arithmeticHandler.mul(bd1, bd2);
          break;
        case S21_DIV:
          if (bd2.equals(BigDecimal.ZERO)) {
            continue;
          }
          res = arithmeticHandler.div(bd1, bd2);
          break;
        default:
      }
      genTry = (validator.checkBigDecimal(res) != 0);
    }

    return Triple.of(bd1, bd2, res);
  }

  private String testHeader() {
    return DONT_FORGET_INCLUDE.getValue();
  }

  private String testTemplate(FunctionNames fName,
                              int count,
                              BigDecimal bd1,
                              BigDecimal bd2,
                              BigDecimal bdCheck) {
    S21Decimal d1 = converter.fromDecToS21Dec(bd1);
    S21Decimal d2 = converter.fromDecToS21Dec(bd2);
    S21Decimal dCheck = converter.fromDecToS21Dec(bdCheck);

    String testName = fName.getValue() + "_" + (count + 1);

    return String.format(TEST_TEMPLATE.getValue(), testName,
        bd1.toPlainString(), d1.extendToString(DEC_1.getValue()),
        bd2.toPlainString(), d2.extendToString(DEC_2.getValue()),
        bdCheck.toPlainString(), dCheck.extendToString(DEC_CHECK.getValue()),
        fName.getValue());
  }

  private String tCaseNamesForFooter(FunctionNames fName, int count) {
    return IntStream.range(1, count + 1)
        .mapToObj(i ->
            String.format(TEST_CASE_NAME_TEMPLATE.getValue(), fName.getValue(), i)
                + System.lineSeparator())
        .collect(Collectors.joining());
  }

  private String footerTestModule(FunctionNames fName, String tCaseNames) {
    return String.format(TEST_SUITE_TEMPLATE.getValue(),
        fName.getValue(), fName.getValue(), fName.getValue(), tCaseNames);
  }
}
