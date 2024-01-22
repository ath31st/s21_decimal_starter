package dec.gen;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
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

  private void addMenu() {

  }
}
