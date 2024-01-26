package dec.starter.constant;

public enum StringConstants {
  SEP("----------------"),
  EXIT("exit"),
  MAIN_MENU("Выберите действие:" + System.lineSeparator()
      + "1. Сложение" + System.lineSeparator()
      + "2. Вычитание" + System.lineSeparator()
      + "3. Умножение" + System.lineSeparator()
      + "4. Деление" + System.lineSeparator()
      + "5. Из десятичного в s21_decimal" + System.lineSeparator()
      + "6. Генератор случайных s21_decimal" + System.lineSeparator()
      + "7. Генератор unit-тестов" + System.lineSeparator()
      + "0. Выход" + System.lineSeparator()),
  GEN_MENU("Выберите функцию, для которой будут сгенерированы тесты:" + System.lineSeparator()
      + "1. s21_add" + System.lineSeparator()
      + "2. s21_sub" + System.lineSeparator()
      + "3. s21_mul" + System.lineSeparator()
      + "4. s21_div" + System.lineSeparator()),
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
