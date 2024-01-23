package dec.gen;

import static dec.gen.StringConstants.END_PROGRAM;
import static dec.gen.StringConstants.EXIT;
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
  private final OutputManager outputManager = new OutputManager();

  public void mainMenu() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      outputManager.consolePrint(MAIN_MENU.getValue());

      int choice = 0;
      try {
        choice = scanner.nextInt();
      } catch (InputMismatchException e) {
        outputManager.consolePrint(WRONG_INPUT.getValue());
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
          outputManager.consolePrint(END_PROGRAM.getValue());
          break;
        default:
          outputManager.consolePrint(WRONG_CHOICE.getValue());
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
      outputManager.consolePrint(INPUT_TWO_NUMBERS.getValue());

      String strVal1 = scanner.next().trim().toLowerCase();
      if (strVal1.equals(EXIT.getValue())) break;
      String strVal2 = scanner.next().trim().toLowerCase();
      if (strVal2.equals(EXIT.getValue())) break;

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
              outputManager.consolePrint(ZERO_DIV.getValue());
            }
            break;
          default:
            break;
        }
        S21Decimal d = converter.fromDecToS21Dec(res);
        outputManager.consolePrintBigDecAndS21Dec(res, d);
      }
    }
  }

  private void generateS21Decimal(Scanner scanner) {
    while (true) {
      outputManager.consolePrint(INPUT_ONE_NUMBER.getValue());

      String strVal = scanner.next().trim().toLowerCase();
      if (strVal.equals(EXIT.getValue())) break;

      if (validator.checkDecimalString(strVal)) {
        BigDecimal bd = converter.fromStrToDec(strVal);
        S21Decimal d = converter.fromDecToS21Dec(bd);
        outputManager.consolePrintBigDecAndS21Dec(bd, d);
      }
    }
  }
}
