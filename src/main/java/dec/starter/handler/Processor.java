package dec.starter.handler;

import static dec.starter.constant.StringConstants.*;

import dec.starter.util.BigDecimalGenerator;
import dec.starter.util.Converter;
import dec.starter.util.OutputManager;
import dec.starter.util.Validator;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Processor {
  private final Validator validator = new Validator();
  private final ArithmeticHandler arithmeticHandler = new ArithmeticHandler();
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
          convertFromNumberToS21Decimal(scanner);
          break;
        case 6:
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

      String strVal1 = readInput(scanner);
      if (strVal1.equals(EXIT.getValue())) break;

      String strVal2 = readInput(scanner);
      if (strVal2.equals(EXIT.getValue())) break;

      performOperation(strVal1, strVal2, action);
    }
  }

  private String readInput(Scanner scanner) {
    return scanner.next().trim().toLowerCase();
  }

  private void performOperation(String strVal1, String strVal2, int action) {
    if (validator.checkDecimalString(strVal1) && validator.checkDecimalString(strVal2)) {
      BigDecimal bd1 = converter.fromStrToDec(strVal1);
      BigDecimal bd2 = converter.fromStrToDec(strVal2);
      BigDecimal res = calculateResult(bd1, bd2, action);

      int check = validator.checkBigDecimal(res);
      handleResultPrinting(res, check);
    }
  }

  private BigDecimal calculateResult(BigDecimal bd1, BigDecimal bd2, int action) {
    BigDecimal res = BigDecimal.ZERO;
    switch (action) {
      case 1:
        res = arithmeticHandler.add(bd1, bd2);
        break;
      case 2:
        res = arithmeticHandler.sub(bd1, bd2);
        break;
      case 3:
        res = arithmeticHandler.mul(bd1, bd2);
        break;
      case 4:
        try {
          res = arithmeticHandler.div(bd1, bd2);
        } catch (IllegalArgumentException e) {
          outputManager.consolePrint(ZERO_DIV.getValue());
        }
        break;
      default:
        break;
    }
    return res;
  }

  private void handleResultPrinting(BigDecimal res, int check) {
    if (check == 1) {
      outputManager.consolePrint(RES_TOO_LARGE_OR_POS_INF.getValue());
    } else if (check == 2) {
      outputManager.consolePrint(RES_TOO_SMALL_OR_POS_NEG.getValue());
    } else {
      outputManager.consolePrintBigDecAndS21Dec(res, "res");
    }
  }

  private void convertFromNumberToS21Decimal(Scanner scanner) {
    while (true) {
      outputManager.consolePrint(INPUT_ONE_NUMBER.getValue());

      String strVal = scanner.next().trim().toLowerCase();
      if (strVal.equals(EXIT.getValue())) break;

      if (validator.checkDecimalString(strVal)) {
        BigDecimal bd = converter.fromStrToDec(strVal);
        outputManager.consolePrintBigDecAndS21Dec(bd, "res");
      }
    }
  }

  private void generateS21Decimal(Scanner scanner) {
    while (true) {
      outputManager.consolePrint(INPUT_ONE_NUMBER.getValue());

      String strVal = scanner.next().trim().toLowerCase();
      if (strVal.equals(EXIT.getValue())) break;
      int count = 0;
      try {
        count = Integer.parseInt(strVal);
      } catch (Exception e) {
        outputManager.consolePrint(WRONG_INPUT.getValue());
      }

      for (int i = 0; i < count; i++) {
        BigDecimal bd = BigDecimalGenerator.generateLimitedBigDecimal();
        outputManager.consolePrintBigDecAndS21Dec(bd, "rand_" + (i + 1));
      }
    }
  }
}
