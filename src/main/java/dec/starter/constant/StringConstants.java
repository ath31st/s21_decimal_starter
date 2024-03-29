package dec.starter.constant;

/**
 * Enumeration of string constants used in the application.
 */
public enum StringConstants {
  SLASH_S("  // %s"),
  SEP("------------------------------------------"),
  DOT_SEP(".........................................."),
  EXIT("exit"),
  MAIN_MENU(SEP.getValue() + System.lineSeparator()
      + "Выберите действие:" + System.lineSeparator()
      + "1. Сложение" + System.lineSeparator()
      + "2. Вычитание" + System.lineSeparator()
      + "3. Умножение" + System.lineSeparator()
      + "4. Деление" + System.lineSeparator()
      + "5. Из десятичного в s21_decimal" + System.lineSeparator()
      + "6. Из s21_decimal в десятичное число" + System.lineSeparator()
      + "7. Генератор случайных s21_decimal" + System.lineSeparator()
      + "8. Генератор unit-тестов" + System.lineSeparator()
      + "9. Удаление папки и файлов с unit-тестами" + System.lineSeparator()
      + "0. Выход" + System.lineSeparator()
      + SEP.getValue()),
  GEN_MENU("Выберите функцию, для которой будут сгенерированы тесты:" + System.lineSeparator()
      + "1. s21_add" + System.lineSeparator()
      + "2. s21_sub" + System.lineSeparator()
      + "3. s21_mul" + System.lineSeparator()
      + "4. s21_div" + System.lineSeparator()
      + "5. Все функции сразу в один файл" + System.lineSeparator()
      + SEP.getValue()),
  WRONG_INPUT("Неправильный ввод, попробуйте еще раз."),
  WRONG_CHOICE("Неверный выбор, попробуйте еще раз."),
  WRONG_OPERAND("Был введен некорректный операнд."),
  END_PROGRAM("Программа завершена."),
  INPUT_TWO_NUMBERS(SEP.getValue() + System.lineSeparator()
      + "Введите два числа или \"exit\" для выхода в предыдущее меню."),
  INPUT_DECIMAL_NUMBER(SEP.getValue() + System.lineSeparator()
      + "Введите десятичное число или \"exit\" для выхода в предыдущее меню."),
  INPUT_S21_DECIMAL_NUMBER(SEP.getValue() + System.lineSeparator()
      + "Введите s21_decimal число или \"exit\" для выхода в предыдущее меню. "
      + "Поддерживается следующий формат ввода: s21_decimal dec = "
      + "{{0xcaed7ac9, 0x849ba54f, 0x31a8, 0x70000}}; " + System.lineSeparator()
      + "Главная часть находится между фигнурными скобками, остальное неважно."),
  INPUT_COUNT_FOR_GEN_DEC(SEP.getValue() + System.lineSeparator() + "Введите количество "
      + "случайных s21_decimal от 1 до 100 или \"exit\" для выхода в предыдущее меню."),
  INPUT_COUNT_FOR_GEN_TEST(SEP.getValue() + System.lineSeparator()
      + "Введите количество тестов от 1 до 500 или \"exit\" для выхода основное меню."),
  ZERO_DIV("Деление на 0 недопустимо."),
  OVERFLOW_VALUES("Введенное число превышает длину %d бит или степень %d%n"),
  RES_TOO_LARGE_OR_POS_INF("Результат слишком велик или положительная бесконечность."),
  RES_TOO_SMALL_OR_POS_NEG("Результат слишком мал или отрицательная бесконечность."),
  RES_NO_FIT_IN_MANTISSA("Результат не вмещается в мантиссу s21_big_decimal."),
  DECIMAL_VALUE("Десятичное значение: "),
  EXCEPTION_IN_THREAD("Возникла ошибка вычисления в потоке: "),
  EXCEPTION_IN_EX_SERVICE("Возникла ошибка в ExecutorService. "),
  REGEXP_HEX_INT("0x[0-9a-fA-F]{1,8}"),
  HB_LOGO("                                            _   _                 "
      + System.lineSeparator()
      + "       _ __   _____      _____ _ __ ___  __| | | |__  _   _       "
      + System.lineSeparator()
      + "      | '_ \\ / _ \\ \\ /\\ / / _ \\ '__/ _ \\/ _` | | '_ \\| | | |      "
      + System.lineSeparator()
      + "      | |_) | (_) \\ V  V /  __/ | |  __/ (_| | | |_) | |_| |      "
      + System.lineSeparator()
      + "   _  | .__/ \\___/ \\_/\\_/ \\___|_|  \\___|\\__,_| |_.__/ \\__, |      "
      + System.lineSeparator()
      + "  | |_|_| ___  _ __   ___ _   _| |__   __ _  __| | __ |___/_ _ __ "
      + System.lineSeparator()
      + "  | '_ \\ / _ \\| '_ \\ / _ \\ | | | '_ \\ / _` |/ _` |/ _` |/ _ \\ '__|"
      + System.lineSeparator()
      + "  | | | | (_) | | | |  __/ |_| | |_) | (_| | (_| | (_| |  __/ |   "
      + System.lineSeparator()
      + "  |_| |_|\\___/|_| |_|\\___|\\__, |_.__/ \\__,_|\\__,_|\\__, |\\___|_|   "
      + System.lineSeparator()
      + "                          |___/                   |___/"
      + System.lineSeparator()
      + "                                                                                        "
      + System.lineSeparator()
      + "    @%     %@@@@@@@@%                                                                   "
      + System.lineSeparator()
      + "  @@@@@@@@@@@%*=---=#@@                                                                 "
      + System.lineSeparator()
      + " @@@%%%%%%%%%@%%=::::-*@@                                                               "
      + System.lineSeparator()
      + " @@@%%%%%%%%%@@@#+-::::+%@                      %@@@@@@@@% @@@@                         "
      + System.lineSeparator()
      + "  @@@%%%%%%%%%%%%%@@@#::*@@                      @@@@%%%%@@@@%@@@%                      "
      + System.lineSeparator()
      + "   @@@@%%%%%%%%%%%%%@@+:=%@                        @@@%%%%%%%%%%@@@                     "
      + System.lineSeparator()
      + "     %@@@@@%%%%%%%%%%*=::*@%                      @@@@@%%%%%%%%%%%@@@@                  "
      + System.lineSeparator()
      + "         @@@@%%%%%%%%%#=::-@@%                    %@@@@%%%%+#%%%%%@@@@@                 "
      + System.lineSeparator()
      + "          @@@%%%%%%%%%%#+-::-#@@@                     %@@*::-*%%%%%%%%@@                "
      + System.lineSeparator()
      + "          @@@@%%%%%%%%%%#+:::::=#@@@@                    @%-:-*#+#%%%%@@                "
      + System.lineSeparator()
      + "           @@@@%%%%%%%%%%#+::::::::+#@@@@@              @@*#+:==-=#%%%@@                "
      + System.lineSeparator()
      + "           @@@@@%%%%%%%%%%#+-::::::::::=*%@@@%          @@%=:::::=#%%%@@@@              "
      + System.lineSeparator()
      + "            @@@@%%%%%%%%%%%#+-:::::::::::::-*%@@@         @@%=:::=***%%%@@@             "
      + System.lineSeparator()
      + "            @@@@%%%%%%%%%%%%%*=-:::::::::::::::+#@@@        @@*::==-*%%%@@@             "
      + System.lineSeparator()
      + "             @@@@%%%%%%%%%%%%%#+=-::::::::::::::::+%@@       @@#-::=#%%%@@              "
      + System.lineSeparator()
      + "             @@@@@%%%%%%%%%%%%%%#*=-::::::::::::::::-*@@      @@+::+%%%@@@              "
      + System.lineSeparator()
      + "             @@@@@%%%%%%%%%@%%%%%%%#+=-:::::::::::::::-#@      @#-=#%%%@@               "
      + System.lineSeparator()
      + "             @@@@@@%%%%%%%@@@%%%%%%%%%#*=-::::-=+*+-::::#@@ @@#+==#%%%@@@               "
      + System.lineSeparator()
      + "             @@@@@@%%%%%%@@@@@@@%%%%%%%%%#**#@@@@@@@@+:::*@@ @@+:=+#%%@@                "
      + System.lineSeparator()
      + "             @@@@@@%%%%%@@@@@@@@@@@%%%%%%%@@@%%%%%%%@@#-::+@@ @#-:=%@@@@                "
      + System.lineSeparator()
      + "             @@@@@@%%%%@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%@%-::+@@@%--*%%@@@                "
      + System.lineSeparator()
      + "             @@@@@@%%%@@@    @@@@@@@@@@@@@@@%%%%%%%%%%%@@+::-%@#=+%%%@@                 "
      + System.lineSeparator()
      + "            @@@@@@%%%%@@@         @@@@@@@@@@%%%%@%%%%%%%%@@-:-+=+%%%@@                  "
      + System.lineSeparator()
      + "           @@@@@@%%%%@@@              @@@@@@%%%@@@@%%%%%%%%#+++*%@@@@                   "
      + System.lineSeparator()
      + "             @@@@%%%@@@                @@@@@%%%@@@@@@@@@@@@@@@@@@@                      "
      + System.lineSeparator()
      + "              @@@@@@@                 @@@%%%%%%@@@@@@@@@@@@@@                           "
      + System.lineSeparator()
      + "                                       @@@@@@@@@@@@@@@@@@                               ");

  private final String value;

  StringConstants(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
