package dec.gen;

import java.math.BigDecimal;

public class OutputManager {
  public void consolePrint(String str) {
    System.out.println(str);
  }

  public void consolePrintBigDecAndS21Dec(BigDecimal bd, S21Decimal d) {
    System.out.println("Десятичное значение: " + bd.toPlainString());
    System.out.println(d.extendToString("res"));
  }

  public String createOkTestTamplate(String handlerName, int count,
                               BigDecimal bd1, S21Decimal d1,
                               BigDecimal bd2, S21Decimal d2,
                               BigDecimal bdCheck, S21Decimal dCheck) {
    String testName = handlerName + "_" + count;
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
        handlerName);
  }
}
