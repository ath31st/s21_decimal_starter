package dec.starter.constant;

import static dec.starter.constant.StringConstants.SLASH_S;

public enum TestStringConstants {
  DONT_FORGET_INCLUDE("#include \"не забудьте здесь добавить свой header.h\""
      + System.lineSeparator()),
  TEST_TEMPLATE("START_TEST(%s) {" + System.lineSeparator()
      + SLASH_S.getValue() + System.lineSeparator()
      + "  %s" + System.lineSeparator()
      + SLASH_S.getValue() + System.lineSeparator()
      + "  %s" + System.lineSeparator()
      + SLASH_S.getValue() + System.lineSeparator()
      + "  %s" + System.lineSeparator()
      + "  s21_decimal result;" + System.lineSeparator()
      + "  int return_value = %s(dec_1, dec_2, &result);" + System.lineSeparator()
      + "  ck_assert_int_eq(return_value, 0);" + System.lineSeparator()
      + "  ck_assert_uint_eq(dec_check.bits[0], result.bits[0]);" + System.lineSeparator()
      + "  ck_assert_uint_eq(dec_check.bits[1], result.bits[1]);" + System.lineSeparator()
      + "  ck_assert_uint_eq(dec_check.bits[2], result.bits[2]);" + System.lineSeparator()
      + "  ck_assert_uint_eq(dec_check.bits[3], result.bits[3]);" + System.lineSeparator()
      + "}" + System.lineSeparator()
      + "END_TEST" + System.lineSeparator()),

  TEST_CASE_NAME_TEMPLATE("  tcase_add_test(tc, %s_%d);"),

  TEST_SUITE_TEMPLATE("Suite *%s_cases(void) {" + System.lineSeparator()
      + "  Suite *c = suite_create(\"%s_cases\");" + System.lineSeparator()
      + "  TCase *tc = tcase_create(\"%s_tc\");" + System.lineSeparator()
      + System.lineSeparator()
      + "%s"
      + System.lineSeparator()
      + "  suite_add_tcase(c, tc);" + System.lineSeparator()
      + "  return c;" + System.lineSeparator()
      + "}" + System.lineSeparator());

  private final String value;

  TestStringConstants(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
