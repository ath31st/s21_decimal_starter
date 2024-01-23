package dec.gen;

import java.math.BigDecimal;

public class TestBuilder {
  private final Converter converter = new Converter();
  public String createOkTestTemplate(String handlerName,
                                     int count,
                                     BigDecimal bd1,
                                     BigDecimal bd2,
                                     BigDecimal bdCheck) {
    S21Decimal d1 = converter.fromDecToS21Dec(bd1);
    S21Decimal d2 = converter.fromDecToS21Dec(bd2);
    S21Decimal dCheck = converter.fromDecToS21Dec(bdCheck);

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
