package dec.starter;

import static dec.starter.constant.S21DecimalNames.DEC_RAND;
import static dec.starter.constant.StringConstants.END_PROGRAM;
import static dec.starter.constant.StringConstants.EXIT;
import static dec.starter.constant.StringConstants.GEN_MENU;
import static dec.starter.constant.StringConstants.INPUT_COUNT_FOR_GEN_DEC;
import static dec.starter.constant.StringConstants.INPUT_COUNT_FOR_GEN_TEST;
import static dec.starter.constant.StringConstants.INPUT_DECIMAL_NUMBER;
import static dec.starter.constant.StringConstants.INPUT_S21_DECIMAL_NUMBER;
import static dec.starter.constant.StringConstants.INPUT_TWO_NUMBERS;
import static dec.starter.constant.StringConstants.MAIN_MENU;
import static dec.starter.constant.StringConstants.WRONG_CHOICE;
import static dec.starter.constant.StringConstants.WRONG_INPUT;
import static dec.starter.constant.StringConstants.WRONG_OPERAND;

import dec.starter.constant.FunctionNames;
import dec.starter.exception.TestBuilderException;
import dec.starter.exception.ValidatorException;
import dec.starter.handler.ArithmeticHandler;
import dec.starter.handler.FileHandler;
import dec.starter.model.S21Decimal;
import dec.starter.util.BigDecimalGenerator;
import dec.starter.util.Converter;
import dec.starter.util.OutputManager;
import dec.starter.util.TestBuilder;
import dec.starter.util.Validator;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main processor class that handles the main menu and various operations in the application.
 */
public class Processor {
  private final Validator validator = new Validator();
  private final Converter converter = new Converter();
  private final ArithmeticHandler arithmeticHandler = new ArithmeticHandler();
  private final OutputManager outputManager = new OutputManager();
  private final FileHandler fileHandler = new FileHandler(outputManager);
  private final TestBuilder testBuilder = new TestBuilder(validator, converter, arithmeticHandler);

  /**
   * Displays the main menu and handles user input for various operations.
   */
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
          arithmeticProcessing(scanner, choice);
          break;
        case 5:
          convertFromNumberToS21Decimal(scanner);
          break;
        case 6:
          convertFromStrS21decimalToBigInteger(scanner);
          break;
        case 7:
          generateS21Decimal(scanner);
          break;
        case 8:
          generateTests(scanner);
          break;
        case 9:
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

  /**
   * Handles arithmetic processing based on the user's choice.
   *
   * @param scanner The Scanner object for user input.
   * @param action  The user's choice representing the arithmetic operation to be performed.
   */
  protected void arithmeticProcessing(Scanner scanner, int action) {
    while (true) {
      outputManager.consolePrint(INPUT_TWO_NUMBERS.getValue());

      String strVal1 = readInput(scanner);
      if (strVal1.equals(EXIT.getValue())) {
        break;
      }

      String strVal2 = readInput(scanner);
      if (strVal2.equals(EXIT.getValue())) {
        break;
      }

      performOperation(strVal1, strVal2, action);
    }
  }

  /**
   * Reads input from the user and trims it.
   *
   * @param scanner The Scanner object for user input.
   * @return The trimmed input string.
   */
  private String readInput(Scanner scanner) {
    return scanner.next().trim().toLowerCase();
  }

  /**
   * Performs the specified arithmetic operation on two BigDecimal values and prints the result.
   *
   * @param strVal1 The string representation of the first BigDecimal operand.
   * @param strVal2 The string representation of the second BigDecimal operand.
   * @param action  The user's choice representing the arithmetic operation to be performed.
   */
  private void performOperation(String strVal1, String strVal2, int action) {
    try {
      BigDecimal bd1 = converter.fromStrToDec(strVal1);
      BigDecimal bd2 = converter.fromStrToDec(strVal2);

      int checkOperand1 = validator.checkBigDecimal(bd1);
      int checkOperand2 = validator.checkBigDecimal(bd2);

      if (checkOperand1 == 0 && checkOperand2 == 0) {
        BigDecimal res = arithmeticHandler.calculateResult(bd1, bd2, action);
        res = arithmeticHandler.additionalRounding(res);
        int check = validator.checkBigDecimal(res);
        outputManager.handleResultPrinting(res, check);
      } else {
        outputManager.consolePrint(WRONG_OPERAND.getValue());
      }
    } catch (ValidatorException e) {
      outputManager.consolePrint(e.getMessage());
    } catch (IllegalArgumentException e) {
      outputManager.consolePrint(WRONG_INPUT.getValue());
    }
  }

  /**
   * Handles conversion from a decimal number to its S21Decimal representation.
   *
   * @param scanner The Scanner object for user input.
   */
  private void convertFromNumberToS21Decimal(Scanner scanner) {
    while (true) {
      outputManager.consolePrint(INPUT_DECIMAL_NUMBER.getValue());

      String strVal = readInput(scanner);
      if (strVal.equals(EXIT.getValue())) {
        break;
      }
      try {
        BigDecimal bd = converter.fromStrToDec(strVal);
        int check = validator.checkBigDecimal(bd);
        outputManager.handleResultPrinting(bd, check);
      } catch (IllegalArgumentException e) {
        outputManager.consolePrint(WRONG_INPUT.getValue());
      }
    }
  }

  /**
   * Read S21 decimal numbers from the provided Scanner, converts them to BigDecimal,
   * and prints the resulting decimal values.
   * The input loop continues until the user
   * enters the specified exit command.
   *
   * @param scanner The Scanner object to read input from.
   */
  private void convertFromStrS21decimalToBigInteger(Scanner scanner) {
    scanner.nextLine();

    while (true) {
      outputManager.consolePrint(INPUT_S21_DECIMAL_NUMBER.getValue());

      String strVal = scanner.nextLine().trim();
      if (strVal.equals(EXIT.getValue())) {
        break;
      }
      try {
        validator.checkStrS21decimal(strVal);
        S21Decimal s21Dec = converter.fromStrToS21Dec(strVal);
        validator.checkSignScaleBits(s21Dec.getSignScaleBits());
        BigDecimal bd = converter.fromS21DecToDec(s21Dec);
        outputManager.printDecimalValueFromBigDecimal(bd);
      } catch (IllegalArgumentException e) {
        outputManager.consolePrint(WRONG_INPUT.getValue());
      }
    }
  }

  /**
   * Generates random S21Decimal numbers based on user input for the count.
   *
   * @param scanner The Scanner object for user input.
   */
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

  /**
   * Generates S21Decimal test cases based on user input for the function name and count.
   *
   * @param scanner The Scanner object for user input.
   */
  private void generateTests(Scanner scanner) {
    outputManager.consolePrint(GEN_MENU.getValue());

    FunctionNames funcName = null;
    while (funcName == null) {
      String strVal = readInput(scanner);
      funcName = getFunctionNameFromInput(strVal);
    }

    outputManager.consolePrint(INPUT_COUNT_FOR_GEN_TEST.getValue());
    String strVal = readInput(scanner);
    while (!strVal.equals(EXIT.getValue())) {
      int count;
      try {
        count = Integer.parseInt(strVal);
        if (count < 1 || count > 500) {
          throw new IllegalArgumentException();
        }
        String generatedTests;
        if (funcName == FunctionNames.ALL_FUNCTIONS) {
          generatedTests = testBuilder.buildAllSuitsAtOnce(count);
        } else {
          generatedTests = testBuilder.buildTestSuit(funcName, count);
          outputManager.consolePrint(generatedTests);
        }
        fileHandler.saveContentToFile(funcName, generatedTests);
        return;
      } catch (IllegalArgumentException e) {
        outputManager.consolePrint(WRONG_INPUT.getValue());
      } catch (TestBuilderException e) {
        outputManager.consolePrint(e.getMessage());
      }
      strVal = readInput(scanner);
    }
  }

  /**
   * Converts a user input string to a FunctionNames enum.
   *
   * @param strVal The user input string.
   * @return The corresponding FunctionNames enum, or null if the conversion fails.
   */
  private FunctionNames getFunctionNameFromInput(String strVal) {
    FunctionNames funcName = null;
    try {
      int val = Integer.parseInt(strVal);
      funcName = FunctionNames.values()[val - 1];
    } catch (Exception e) {
      outputManager.consolePrint(WRONG_INPUT.getValue());
    }
    return funcName;
  }
}
