package dec.starter.constant;

public enum StringConstants {
  SEP("----------------"),
  EXIT("exit"),
  MAIN_MENU("Выберите действие:\n1. Сложение\n2. Вычитание\n"
      + "3. Умножение\n4. Деление\n5. Из десятичного в s21_decimal"
      + "\n6. Генерировать случайные s21_decimal\n0. Выход"),
  WRONG_INPUT("Неправильный ввод, попробуйте еще раз."),
  WRONG_CHOICE("Неверный выбор, попробуйте еще раз."),
  END_PROGRAM("Программа завершена."),
  INPUT_TWO_NUMBERS("Введите два числа или \"exit\" для выхода в предыдущее меню."),
  INPUT_ONE_NUMBER("Введите число или \"exit\" для выхода в предыдущее меню."),
  ZERO_DIV("Деление на 0 недопустимо."),
  OVERFLOW_VALUES("Введенное число превышает объем %d бит или степень %d%n"),
  RES_TOO_LARGE_OR_POS_INF("Результат слишком велик или положительная бесконечность."),
  RES_TOO_SMALL_OR_POS_NEG("Результат слишком мал или отрицательная бесконечность.");

  private final String value;

  StringConstants(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
