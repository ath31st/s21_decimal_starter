package dec.starter.constant;

public enum FunctionNames {
  S21_ADD("s21_add"),
  S21_SUB("s21_sub"),
  S21_DIV("s21_div"),
  S21_MUL("s21_mul");
  private final String value;

  FunctionNames(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
