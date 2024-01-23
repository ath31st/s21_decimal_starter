package dec.gen;

import static dec.gen.StringConstants.END_PROGRAM;
import static dec.gen.StringConstants.INPUT_ONE_NUMBER;
import static dec.gen.StringConstants.INPUT_TWO_NUMBERS;
import static dec.gen.StringConstants.MAIN_MENU;
import static dec.gen.StringConstants.WRONG_CHOICE;
import static dec.gen.StringConstants.WRONG_INPUT;
import static dec.gen.StringConstants.ZERO_DIV;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
  private final Validator validator = new Validator();
  private final ArithmeticHandlers arithmeticHandlers = new ArithmeticHandlers();
  private final Converter converter = new Converter();

  public void mainMenu() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println(MAIN_MENU.getValue());

      int choice = 0;
      try {
        choice = scanner.nextInt();
      } catch (InputMismatchException e) {
        System.out.println(WRONG_INPUT.getValue());
        mainMenu();
      }

      switch (choice) {
        case 1:
        case 2:
        case 3:
        case 4:
          processing(scanner, choice);
          break;
        case 5:
          generateS21Decimal(scanner);
          break;
        case 0:
          System.out.println(END_PROGRAM.getValue());
          break;
        default:
          System.out.println(WRONG_CHOICE.getValue());
      }
      if (choice == 0) {
        scanner.close();
        break;
      }
    }
    System.exit(0);
  }

  private void processing(Scanner scanner, int action) {
    while (true) {
      System.out.println(INPUT_TWO_NUMBERS.getValue());

      String strVal1 = scanner.next().trim();
      if (strVal1.equals("exit")) break;
      String strVal2 = scanner.next().trim();
      if (strVal2.equals("exit")) break;

      if (validator.checkDecimalString(strVal1) && validator.checkDecimalString(strVal2)) {
        BigDecimal bd1 = converter.fromStrToDec(strVal1);
        BigDecimal bd2 = converter.fromStrToDec(strVal2);
        BigDecimal res = BigDecimal.ZERO;
        switch (action) {
          case 1:
            res = arithmeticHandlers.add(bd1, bd2);
            break;
          case 2:
            res = arithmeticHandlers.sub(bd1, bd2);
            break;
          case 3:
            res = arithmeticHandlers.mul(bd1, bd2);
            break;
          case 4:
            try {
              res = arithmeticHandlers.div(bd1, bd2);
            } catch (IllegalArgumentException e) {
              System.out.println(ZERO_DIV.getValue());
            }
            break;
          default:
            break;
        }
        S21Decimal d = converter.fromDecToS21Dec(res);
        System.out.println(res.toPlainString());
        System.out.println(d);
      }
    }
  }

  private void generateS21Decimal(Scanner scanner) {
    while (true) {
      System.out.println(INPUT_ONE_NUMBER.getValue());

      String strVal = scanner.next().trim();
      if (strVal.equals("exit")) break;

      if (validator.checkDecimalString(strVal)) {
        BigDecimal bd = converter.fromStrToDec(strVal);
        S21Decimal d = converter.fromDecToS21Dec(bd);
        System.out.println(d);
      }
    }
  }
}
