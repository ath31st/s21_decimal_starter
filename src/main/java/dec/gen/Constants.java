package dec.gen;

public enum Constants {
  MAX_BIT_LENGTH(96),
  MAX_SCALE(28);

  private Integer value;

  Constants(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }
}
