package dec.starter.util;

import dec.starter.constant.ArithmeticConstants;
import dec.starter.model.S21Decimal;
import dec.starter.constant.FunctionNames;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.lang3.tuple.Triple;

public class TestBuilder {
  private final Validator validator;
  private final Converter converter = new Converter();

  public TestBuilder(Validator validator) {
    this.validator = validator;
  }

  private String buildTestSuit(FunctionNames fName, int count) {
    StringBuilder sb = new StringBuilder();
    sb.append(testHeader());
    for (int i = 0; i < count; i++) {
      Triple<BigDecimal, BigDecimal, BigDecimal> t = generateOkValues(fName);
      sb.append(testTemplate(fName, i, t.getLeft(), t.getMiddle(), t.getRight()));
    }
    sb.append(footerTestModule(fName, tCaseNamesForFooter(fName, count)));

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
          res = bd1.add(bd2);
          break;
        case S21_SUB:
          res = bd1.subtract(bd2);
          break;
        case S21_MUL:
          res = bd1.multiply(bd2);
          break;
        case S21_DIV:
          if (bd2.equals(BigDecimal.ZERO)) {
            continue;
          }
          res = bd1.divide(bd2, ArithmeticConstants.MAX_SCALE.getValue(),
              RoundingMode.HALF_EVEN).stripTrailingZeros();
          break;
        default:
      }
      genTry = (validator.checkBigDecimal(res) != 0);
    }

    return Triple.of(bd1, bd2, res);
  }

  private String testHeader() {
    return "#include \"не забудьте добавить здесь свой header.h\"\n";
  }

  private String testTemplate(FunctionNames fName,
                              int count,
                              BigDecimal bd1,
                              BigDecimal bd2,
                              BigDecimal bdCheck) {
    S21Decimal d1 = converter.fromDecToS21Dec(bd1);
    S21Decimal d2 = converter.fromDecToS21Dec(bd2);
    S21Decimal dCheck = converter.fromDecToS21Dec(bdCheck);

    String testName = fName.getValue() + "_" + count;
    return String.format("START_TEST(%s) {\n" +
            "  // %s\n" +
            "  %s\n" +
            "  // %s\n" +
            "  %s\n" +
            "  // %s\n" +
            "  %s\n" +
            "  s21_decimal result;\n" +
            "  int return_value = %s(dec_1, dec_2, &result);\n" +
            "  ck_assert_int_eq(return_value, OK);\n" +
            "  ck_assert_uint_eq(check.bits[0], result.bits[0]);\n" +
            "  ck_assert_uint_eq(check.bits[1], result.bits[1]);\n" +
            "  ck_assert_uint_eq(check.bits[2], result.bits[2]);\n" +
            "  ck_assert_uint_eq(check.bits[3], result.bits[3]);\n" +
            "}\n" +
            "END_TEST",
        testName,
        bd1.toPlainString(), d1.extendToString("dec_1"),
        bd2.toPlainString(), d2.extendToString("dec_2"),
        bdCheck.toPlainString(), dCheck.extendToString("check"),
        fName.getValue());
  }

  private String tCaseNamesForFooter(FunctionNames fName, int count) {
    return IntStream.range(1, count + 1)
        .mapToObj(i -> String.format("tcase_add_test(tc, %s_%d);\n", fName.getValue(), i))
        .collect(Collectors.joining());
  }

  private String footerTestModule(FunctionNames fName, String tCaseNames) {
    return String.format("Suite *%s_cases(void) {\n" +
        "  Suite *c = suite_create(\"%s_cases\");\n" +
        "  TCase *tc = tcase_create(\"%s_tc\");\n" +
        "\n" +
        tCaseNames +
        "  suite_add_tcase(c, tc);\n" +
        "  return c;\n" +
        "}", fName.getValue(), fName.getValue(), fName.getValue(), tCaseNames);
  }
}
