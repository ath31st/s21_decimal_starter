package dec.starter.constant;

public enum StringConstants {
  SEP("----------------"),
  EXIT("exit"),
  MAIN_MENU("Выберите действие:\n1. Сложение\n2. Вычитание\n"
      + "3. Умножение\n4. Деление\n5. Из десятичного в s21_decimal"
      + "\n6. Генератор случайных s21_decimal\n7. Генератор тестов\n0. Выход"),
  GEN_MENU("Выберите функцию, для которой будут сгенерированы тесты:"
      + "\n1. s21_add\n2. s21_sub\n3. s21_mul\n4. s21_div"),
  WRONG_INPUT("Неправильный ввод, попробуйте еще раз."),
  WRONG_CHOICE("Неверный выбор, попробуйте еще раз."),
  END_PROGRAM("Программа завершена."),
  INPUT_TWO_NUMBERS("Введите два числа или \"exit\" для выхода в предыдущее меню."),
  INPUT_DECIMAL_NUMBER("Введите десятичное число или \"exit\" для выхода в предыдущее меню."),
  INPUT_COUNT_FOR_GEN_DEC("Введите количество случайных s21_decimal от 1 до 100 или \"exit\" для выхода в предыдущее меню."),
  INPUT_COUNT_FOR_GEN_TEST("Введите количество тестов от 1 до 100 или \"exit\" для выхода основное меню."),
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
