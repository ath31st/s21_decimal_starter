package dec.gen;

public enum StringConstants {
  EXIT("exit"),
  MAIN_MENU("Выберите действие:\n1. Сложение\n2. Вычитание\n"
      + "3. Умножение\n4. Деление\n5. Генерация числа\n0. Выход"),
  WRONG_INPUT("Неправильный ввод, попробуйте еще раз."),
  WRONG_CHOICE("Неверный выбор, попробуйте еще раз."),
  END_PROGRAM("Программа завершена."),
  INPUT_TWO_NUMBERS("Введите два числа или exit для выхода в предыдущее меню."),
  INPUT_ONE_NUMBER("Введите число или exit для выхода в предыдущее меню."),
  ZERO_DIV("Деление на 0 недопустимо."),
  OVERFLOW_VALUES("Введенное число превышает объем %d бит или степень %d%n");
  private final String value;

  StringConstants(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
