package dec.gen;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
  private final Validator validator = new Validator();
  private final Arithmetic arithmetic = new Arithmetic();
  private final Converter converter = new Converter();

  public void mainMenu() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("Выберите действие:\n1. Сложение\n2. Вычитание\n"
          + "3. Умножение\n4. Деление\n5. Генерация числа\n0. Выход");

      int choice = 0;
      try {
        choice = scanner.nextInt();
      } catch (InputMismatchException e) {
        System.out.println("Неправильный ввод, попробуйте еще раз.");
        mainMenu();
      }

      switch (choice) {
        case 1:
          addMenu(scanner, validator);
          break;
        case 2:
        case 3:
        case 4:
          System.out.println("1-4");
          break;
        case 5:
          System.out.println("5");
          break;
        case 0:
          System.out.println("Программа завершена.");
          break;
        default:
          System.out.println("Неверный выбор, попробуйте еще раз.");
      }
      if (choice == 0) {
        scanner.close();
        break;
      }
    }
    System.exit(0);
  }

  private void addMenu(Scanner scanner, Validator validator) {
    while (true) {
      System.out.println("Введите два числа или exit для выхода в предыдущее меню.");

      String strVal1 = scanner.next().trim();
      if (strVal1.equals("exit")) break;
      String strVal2 = scanner.next().trim();
      if (strVal2.equals("exit")) break;

      if (validator.checkDecimalString(strVal1) && validator.checkDecimalString(strVal2)) {
        BigDecimal bd1 = converter.fromStrToDec(strVal1);
        BigDecimal bd2 = converter.fromStrToDec(strVal2);
        BigDecimal res = arithmetic.add(bd1, bd2);
        S21Decimal d = converter.fromDecToS21Dec(res);
        System.out.println(d);
      }
    }
  }
}
