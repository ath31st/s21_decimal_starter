package dec.starter.constant;

public enum S21DecimalNames {
  DEC_1("dec_1"),
  DEC_2("dec_2"),
  DEC_RES("dec_res"),
  DEC_CHECK("dec_check"),
  DEC_RAND("dec_rand_");
  private final String value;

  S21DecimalNames(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
