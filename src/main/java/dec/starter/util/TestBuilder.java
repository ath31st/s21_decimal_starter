package dec.starter.util;

import static dec.starter.constant.S21DecimalNames.DEC_1;
import static dec.starter.constant.S21DecimalNames.DEC_2;
import static dec.starter.constant.S21DecimalNames.DEC_CHECK;
import static dec.starter.constant.StringConstants.EXCEPTION_IN_EX_SERVICE;
import static dec.starter.constant.StringConstants.EXCEPTION_IN_THREAD;
import static dec.starter.constant.StringConstants.RES_TOO_LARGE_OR_POS_INF;
import static dec.starter.constant.StringConstants.RES_TOO_SMALL_OR_POS_NEG;
import static dec.starter.constant.TestStringConstants.DONT_FORGET_INCLUDE;
import static dec.starter.constant.TestStringConstants.TEST_FAIL_CASE_NAME_TEMPLATE;
import static dec.starter.constant.TestStringConstants.TEST_FAIL_TEMPLATE;
import static dec.starter.constant.TestStringConstants.TEST_INVALID_DECIMAL_TEMPLATE;
import static dec.starter.constant.TestStringConstants.TEST_INVALID_DEC_CASE_NAME_TEMPLATE;
import static dec.starter.constant.TestStringConstants.TEST_OK_CASE_NAME_TEMPLATE;
import static dec.starter.constant.TestStringConstants.TEST_OK_TEMPLATE;
import static dec.starter.constant.TestStringConstants.TEST_SUITE_TEMPLATE;

import dec.starter.constant.FunctionNames;
import dec.starter.exception.TestBuilderException;
import dec.starter.handler.ArithmeticHandler;
import dec.starter.model.S21Decimal;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.lang3.tuple.Triple;

/**
 * TestBuilder class for generating test suites and cases for various arithmetic functions.
 */
public class TestBuilder {
  private final Validator validator;
  private final Converter converter;
  private final ArithmeticHandler arithmeticHandler;
  /**
   * Constant representing the percentage of total counts used in test generation.
   */
  private static final int PART_OF_TOTAL_COUNTS = 3; // 33%
  /**
   * Constant representing the count of invalid decimal tests.
   */
  private static final int INVALID_DEC_TESTS_COUNT = 16;

  /**
   * Constructor for TestBuilder.
   *
   * @param validator         Validator instance for input validation.
   * @param converter         Converter instance for converting between different decimal
   *                          representations.
   * @param arithmeticHandler ArithmeticHandler instance for performing arithmetic operations.
   */
  public TestBuilder(Validator validator,
                     Converter converter,
                     ArithmeticHandler arithmeticHandler) {
    this.validator = validator;
    this.converter = converter;
    this.arithmeticHandler = arithmeticHandler;
  }

  /**
   * Builds test suites for all arithmetic functions at once.
   *
   * @param count The number of test cases to generate for each function.
   * @return The generated test suites.
   */
  public String buildAllSuitsAtOnce(int count) {
    return buildTestSuitCommon(
        FunctionNames.ALL_FUNCTIONS,
        count,
        Arrays.stream(FunctionNames.values())
            .filter(f -> f != FunctionNames.ALL_FUNCTIONS)
            .collect(Collectors.toList()));
  }

  /**
   * Builds a test suite for a specific arithmetic function.
   *
   * @param funcName The arithmetic function for which to generate the test suite.
   * @param count    The number of test cases to generate for the specified function.
   * @return The generated test suite.
   */
  public String buildTestSuit(FunctionNames funcName, int count) {
    return buildTestSuitCommon(funcName, count, Collections.singletonList(funcName));
  }

  /**
   * Builds a test suite for a specific arithmetic function or footer based on the provided
   * parameters.
   *
   * @param footerFuncName The arithmetic function or footer for which to generate the test suite.
   * @param count          The number of test cases to generate for each specified function.
   * @param functionList   The list of arithmetic functions to include in the test suite.
   * @return The generated test suite.
   */
  private String buildTestSuitCommon(FunctionNames footerFuncName,
                                     int count,
                                     List<FunctionNames> functionList) {
    ExecutorService executorService
        = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    StringBuilder sb = new StringBuilder();
    sb.append(testHeader()).append(System.lineSeparator());

    List<Future<String>> futures = functionList.stream()
        .map(currentFName -> executorService.submit(
            () -> buildTestsForCurrentFuncName(count, currentFName)))
        .collect(Collectors.toList());

    futures.forEach(f -> {
      try {
        sb.append(f.get());
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new TestBuilderException(EXCEPTION_IN_THREAD.getValue()
            + Thread.currentThread().getName());
      } catch (ExecutionException e) {
        throw new TestBuilderException(EXCEPTION_IN_EX_SERVICE.getValue() + e.getMessage());
      }
    });

    String commonTestCaseNames = functionList.stream()
        .map(f -> testCaseInvalidDecNamesForFooter(f) + testCaseOkNamesForFooter(f, count)
            + testCaseFailNamesForFooter(f, count / PART_OF_TOTAL_COUNTS))
        .collect(Collectors.joining(System.lineSeparator()));

    sb.append(footerTestModule(footerFuncName, commonTestCaseNames));

    return sb.toString();
  }

  /**
   * Builds tests for the specified arithmetic function and count.
   *
   * @param count           The number of test cases to generate for the specified function.
   * @param currentFuncName The current arithmetic function for which to generate tests.
   * @return The generated tests for the specified function.
   */
  private String buildTestsForCurrentFuncName(int count, FunctionNames currentFuncName) {
    StringBuilder sb = new StringBuilder();
    sb.append(testInvalidTemplate(currentFuncName));
    sb.append(System.lineSeparator());

    for (int i = 0; i < count; i++) {
      Triple<BigDecimal, BigDecimal, BigDecimal> t = generateOkValues(currentFuncName);
      sb.append(testOkTemplate(currentFuncName, i, t.getLeft(), t.getMiddle(), t.getRight()));
      sb.append(System.lineSeparator());
    }

    for (int i = 0; i < count / PART_OF_TOTAL_COUNTS; i++) {
      Triple<BigDecimal, BigDecimal, Integer> t = generateFailValues(currentFuncName);
      sb.append(testFailTemplate(currentFuncName, i, t.getLeft(), t.getMiddle(), t.getRight()));
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }

  /**
   * Generates valid values for an arithmetic function for test cases.
   *
   * @param funcName The arithmetic function for which to generate valid values.
   * @return A Triple containing valid BigDecimal values for the function.
   */
  private Triple<BigDecimal, BigDecimal, BigDecimal> generateOkValues(FunctionNames funcName) {
    boolean genTry = true;
    BigDecimal bd1 = BigDecimal.ZERO;
    BigDecimal bd2 = BigDecimal.ZERO;
    BigDecimal res = BigDecimal.ZERO;

    while (genTry) {
      bd1 = BigDecimalGenerator.generateLimitedBigDecimal();
      bd2 = BigDecimalGenerator.generateLimitedBigDecimal();

      if (funcName == FunctionNames.S21_DIV && bd2.compareTo(BigDecimal.ZERO) == 0) {
        continue;
      }

      res = arithmeticHandler.calculateResult(bd1, bd2, funcName);
      genTry = (validator.checkBigDecimal(res) != 0);
    }

    return Triple.of(bd1, bd2, res);
  }

  /**
   * Generates invalid values for an arithmetic function for test cases.
   *
   * @param funcName The arithmetic function for which to generate invalid values.
   * @return A Triple containing invalid BigDecimal values and a fail code.
   */
  private Triple<BigDecimal, BigDecimal, Integer> generateFailValues(FunctionNames funcName) {
    boolean genTry = true;
    int failCode = 0;
    BigDecimal bd1 = BigDecimal.ZERO;
    BigDecimal bd2 = BigDecimal.ZERO;
    BigDecimal res;

    while (genTry) {
      bd1 = BigDecimalGenerator.generateLimitedBigDecimal();
      bd2 = BigDecimalGenerator.generateLimitedBigDecimal();

      if (funcName == FunctionNames.S21_DIV && bd2.compareTo(BigDecimal.ZERO) == 0) {
        continue;
      }

      res = arithmeticHandler.calculateResult(bd1, bd2, funcName);
      failCode = validator.checkBigDecimal(res);
      genTry = (failCode == 0);
    }

    return Triple.of(bd1, bd2, failCode);
  }

  /**
   * Creates the header for the generated test suite.
   *
   * @return The header string for the test suite.
   */
  private String testHeader() {
    return DONT_FORGET_INCLUDE.getValue();
  }

  /**
   * Generates the test template for successful test cases.
   *
   * @param funcName The arithmetic function for which to generate the test template.
   * @param count    The index of the test case.
   * @param bd1      The first BigDecimal operand.
   * @param bd2      The second BigDecimal operand.
   * @param bdCheck  The result of the arithmetic operation.
   * @return The generated test template for successful test cases.
   */
  private String testOkTemplate(FunctionNames funcName,
                                int count,
                                BigDecimal bd1,
                                BigDecimal bd2,
                                BigDecimal bdCheck) {
    S21Decimal d1 = converter.fromDecToS21Dec(bd1);
    S21Decimal d2 = converter.fromDecToS21Dec(bd2);
    S21Decimal decimalCheck = converter.fromDecToS21Dec(bdCheck);

    String testName = funcName.getValue() + "_" + (count + 1);

    return String.format(TEST_OK_TEMPLATE.getValue(), testName,
        bd1.toPlainString(), d1.extendToString(DEC_1.getValue()),
        bd2.toPlainString(), d2.extendToString(DEC_2.getValue()),
        bdCheck.toPlainString(), decimalCheck.extendToString(DEC_CHECK.getValue()),
        funcName.getValue());
  }

  /**
   * Generates the test template for failed test cases.
   *
   * @param funcName The arithmetic function for which to generate the test template.
   * @param count    The index of the test case.
   * @param bd1      The first BigDecimal operand.
   * @param bd2      The second BigDecimal operand.
   * @param failCode The fail code indicating the reason for failure.
   * @return The generated test template for failed test cases.
   */
  private String testFailTemplate(FunctionNames funcName,
                                  int count,
                                  BigDecimal bd1,
                                  BigDecimal bd2,
                                  int failCode) {
    S21Decimal d1 = converter.fromDecToS21Dec(bd1);
    S21Decimal d2 = converter.fromDecToS21Dec(bd2);

    String testName = "fail_" + funcName.getValue() + "_" + (count + 1);
    String failInfo = failCode == 1
        ? RES_TOO_LARGE_OR_POS_INF.getValue() : RES_TOO_SMALL_OR_POS_NEG.getValue();

    return String.format(TEST_FAIL_TEMPLATE.getValue(), testName,
        bd1.toPlainString(), d1.extendToString(DEC_1.getValue()),
        bd2.toPlainString(), d2.extendToString(DEC_2.getValue()),
        failCode, failInfo,
        funcName.getValue());
  }

  /**
   * Generates the test template for invalid decimal values.
   *
   * @param funcName The arithmetic function for which to generate the test template.
   * @return The generated test template for invalid decimal values.
   */
  private String testInvalidTemplate(FunctionNames funcName) {
    String testName = funcName.getValue();

    return String.format(TEST_INVALID_DECIMAL_TEMPLATE.getValue(),
        testName, testName, testName, testName, testName, testName, testName, testName, testName,
        testName, testName, testName, testName, testName, testName, testName, testName, testName,
        testName, testName, testName, testName, testName, testName, testName, testName, testName,
        testName, testName, testName, testName, testName, testName, testName
    );
  }

  /**
   * Generates the names for test cases with invalid decimal values for the specified arithmetic
   * function.
   *
   * @param funcName The arithmetic function for which to generate the names.
   * @return The generated names for test cases with invalid decimal values.
   */
  private String testCaseInvalidDecNamesForFooter(FunctionNames funcName) {
    return IntStream.range(1, INVALID_DEC_TESTS_COUNT + 1)
        .mapToObj(i ->
            String.format(TEST_INVALID_DEC_CASE_NAME_TEMPLATE.getValue(), funcName.getValue(), i)
                + System.lineSeparator())
        .collect(Collectors.joining());
  }

  /**
   * Generates the names for successful test cases for the specified arithmetic function.
   *
   * @param funcName The arithmetic function for which to generate the names.
   * @param count    The number of successful test cases.
   * @return The generated names for successful test cases.
   */
  private String testCaseOkNamesForFooter(FunctionNames funcName, int count) {
    return IntStream.range(1, count + 1)
        .mapToObj(i ->
            String.format(TEST_OK_CASE_NAME_TEMPLATE.getValue(), funcName.getValue(), i)
                + System.lineSeparator())
        .collect(Collectors.joining());
  }

  /**
   * Generates the names for failed test cases for the specified arithmetic function.
   *
   * @param funcName The arithmetic function for which to generate the names.
   * @param count    The number of failed test cases.
   * @return The generated names for failed test cases.
   */
  private String testCaseFailNamesForFooter(FunctionNames funcName, int count) {
    return IntStream.range(1, count + 1)
        .mapToObj(i ->
            String.format(TEST_FAIL_CASE_NAME_TEMPLATE.getValue(), funcName.getValue(), i)
                + System.lineSeparator())
        .collect(Collectors.joining());
  }

  /**
   * Generates the footer module for the entire test suite.
   *
   * @param funcName      The arithmetic function or footer for which to generate the footer module.
   * @param testCaseNames The names of the test cases to include in the footer module.
   * @return The generated footer module for the entire test suite.
   */
  private String footerTestModule(FunctionNames funcName, String testCaseNames) {
    return String.format(TEST_SUITE_TEMPLATE.getValue(),
        funcName.getValue(), funcName.getValue(), funcName.getValue(), testCaseNames);
  }
}
