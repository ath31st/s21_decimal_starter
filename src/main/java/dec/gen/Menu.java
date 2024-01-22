package dec.gen;

import java.util.Scanner;
import java.util.logging.Logger;

public class Menu {
  Logger log = Logger.getLogger(Menu.class.getName());

  public void mainMenu() {

    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("Выберите действие:\n1. Сложение\n2. Вычитание\n"
          + "3. Умножение\n4. Деление\n5. Генерация числа\n0. Выход");

      int choice = scanner.nextInt();

      switch (choice) {
        case 1:
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
          System.out.println("Неверный выбор. Попробуйте еще раз.");
      }
      if (choice == 0) {
        scanner.close();
        break;
      }
    }
    System.exit(0);
  }
}
