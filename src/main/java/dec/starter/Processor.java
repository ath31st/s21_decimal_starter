package dec.starter;

import static dec.starter.constant.S21DecimalNames.DEC_RAND;
import static dec.starter.constant.S21DecimalNames.DEC_RES;
import static dec.starter.constant.StringConstants.END_PROGRAM;
import static dec.starter.constant.StringConstants.EXIT;
import static dec.starter.constant.StringConstants.GEN_MENU;
import static dec.starter.constant.StringConstants.INPUT_COUNT_FOR_GEN_DEC;
import static dec.starter.constant.StringConstants.INPUT_COUNT_FOR_GEN_TEST;
import static dec.starter.constant.StringConstants.INPUT_DECIMAL_NUMBER;
import static dec.starter.constant.StringConstants.INPUT_TWO_NUMBERS;
import static dec.starter.constant.StringConstants.MAIN_MENU;
import static dec.starter.constant.StringConstants.RES_TOO_LARGE_OR_POS_INF;
import static dec.starter.constant.StringConstants.RES_TOO_SMALL_OR_POS_NEG;
import static dec.starter.constant.StringConstants.WRONG_CHOICE;
import static dec.starter.constant.StringConstants.WRONG_INPUT;
import static dec.starter.constant.StringConstants.ZERO_DIV;

import dec.starter.constant.FunctionNames;
import dec.starter.exception.ValidatorException;
import dec.starter.handler.ArithmeticHandler;
import dec.starter.handler.FileHandler;
import dec.starter.util.BigDecimalGenerator;
import dec.starter.util.Converter;
import dec.starter.util.OutputManager;
import dec.starter.util.TestBuilder;
import dec.starter.util.Validator;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Processor {
  private final Validator validator = new Validator();
  private final Converter converter = new Converter();
  private final ArithmeticHandler arithmeticHandler = new ArithmeticHandler();
  private final OutputManager outputManager = new OutputManager();
  private final FileHandler fileHandler = new FileHandler(outputManager);
  private final TestBuilder testBuilder = new TestBuilder(validator, converter, arithmeticHandler);

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
        case 7:
          generateTests(scanner);
          break;
        case 8:
          fileHandler.deleteFilesAndDirectory();
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
    try {
      validator.checkDecimalString(strVal1);
      validator.checkDecimalString(strVal2);

      BigDecimal bd1 = converter.fromStrToDec(strVal1);
      BigDecimal bd2 = converter.fromStrToDec(strVal2);
      BigDecimal res = calculateResult(bd1, bd2, action);

      int check = validator.checkBigDecimal(res);
      handleResultPrinting(res, check);
    } catch (ValidatorException e) {
      outputManager.consolePrint(e.getMessage());
    } catch (IllegalArgumentException e) {
      outputManager.consolePrint(ZERO_DIV.getValue());
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
        res = arithmeticHandler.div(bd1, bd2);
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
      outputManager.consolePrintBigDecAndS21Dec(res, DEC_RES.getValue());
    }
  }

  private void convertFromNumberToS21Decimal(Scanner scanner) {
    while (true) {
      outputManager.consolePrint(INPUT_DECIMAL_NUMBER.getValue());

      String strVal = readInput(scanner);
      if (strVal.equals(EXIT.getValue())) break;
      try {
        validator.checkDecimalString(strVal);
        BigDecimal bd = converter.fromStrToDec(strVal);
        outputManager.consolePrintBigDecAndS21Dec(bd, DEC_RES.getValue());
      } catch (ValidatorException e) {
        outputManager.consolePrint(e.getMessage());
      }
    }
  }

  private void generateS21Decimal(Scanner scanner) {
    while (true) {
      outputManager.consolePrint(INPUT_COUNT_FOR_GEN_DEC.getValue());

      String strVal = scanner.next().trim().toLowerCase();
      if (strVal.equals(EXIT.getValue())) {
        break;
      }
      try {
        int count = Integer.parseInt(strVal);
        if (count < 1 || count > 100) {
          throw new IllegalArgumentException();
        }
        for (int i = 0; i < count; i++) {
          BigDecimal bd = BigDecimalGenerator.generateLimitedBigDecimal();
          outputManager.consolePrintBigDecAndS21Dec(bd, DEC_RAND.getValue() + (i + 1));
        }
      } catch (Exception e) {
        outputManager.consolePrint(WRONG_INPUT.getValue());
      }
    }
  }

  private void generateTests(Scanner scanner) {
    outputManager.consolePrint(GEN_MENU.getValue());
    String strVal = readInput(scanner);
    FunctionNames fName = null;
    try {
      int val = Integer.parseInt(strVal);
      fName = FunctionNames.values()[val - 1];
    } catch (Exception e) {
      outputManager.consolePrint(WRONG_INPUT.getValue());
      generateTests(scanner);
    }
    outputManager.consolePrint(INPUT_COUNT_FOR_GEN_TEST.getValue());
    while (!strVal.equals(EXIT.getValue())) {
      strVal = readInput(scanner);
      int count;
      try {
        count = Integer.parseInt(strVal);
        if (count < 1 || count > 100) {
          throw new IllegalArgumentException();
        }
        String generatedTests = testBuilder.buildTestSuit(fName, count);
        outputManager.consolePrint(generatedTests);
        fileHandler.saveContentToFile(fName, generatedTests);
        break;
      } catch (IllegalArgumentException e) {
        outputManager.consolePrint(WRONG_INPUT.getValue());
      }
    }
  }
}
