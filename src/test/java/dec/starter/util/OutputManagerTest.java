package dec.starter.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dec.starter.constant.StringConstants;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OutputManagerTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  void restoreStreams() {
    System.setOut(originalOut);
  }


  @Test
  void consolePrint() {
    OutputManager outputManager = new OutputManager();
    System.setOut(new java.io.PrintStream(outContent));

    outputManager.consolePrint("Test Console Print");

    assertEquals("Test Console Print" + System.lineSeparator(), outContent.toString());
    System.setOut(originalOut);
  }

  @Test
  void consolePrintBigDecAndS21Dec() {
    OutputManager outputManager = new OutputManager();
    // Redirect System.out to capture printed text
    System.setOut(new java.io.PrintStream(outContent));

    BigDecimal bd = BigDecimal.valueOf(123.45);
    String decimalName = "TestDecimal";
    outputManager.consolePrintBigDecAndS21Dec(bd, decimalName);

    String expectedOutput = StringConstants.DOT_SEP.getValue() + System.lineSeparator() +
        StringConstants.DECIMAL_VALUE.getValue() + bd.toPlainString() + System.lineSeparator() +
        "s21_decimal TestDecimal = {{0x3039, 0x0, 0x0, 0x20000}};" + System.lineSeparator();

    assertEquals(expectedOutput, outContent.toString());
    System.setOut(originalOut);
  }

  @Test
  void handleResultPrinting_WhenCheckIs1() {
    OutputManager outputManager = new OutputManager();
    System.setOut(new java.io.PrintStream(outContent));

    BigDecimal res = BigDecimal.valueOf(123.45);

    outputManager.handleResultPrinting(res, 1);

    assertEquals(StringConstants.RES_TOO_LARGE_OR_POS_INF.getValue()
        + System.lineSeparator(), outContent.toString());

    System.setOut(originalOut);
  }

  @Test
  void handleResultPrinting_WhenCheckIs2() {
    OutputManager outputManager = new OutputManager();
    System.setOut(new java.io.PrintStream(outContent));

    BigDecimal res = BigDecimal.valueOf(123.45);

    outputManager.handleResultPrinting(res, 2);

    assertEquals(StringConstants.RES_TOO_SMALL_OR_POS_NEG.getValue()
        + System.lineSeparator(), outContent.toString());

    System.setOut(originalOut);
  }

  @Test
  void handleResultPrinting_WhenCheckIs3() {
    OutputManager outputManager = new OutputManager();
    System.setOut(new java.io.PrintStream(outContent));

    BigDecimal res = BigDecimal.valueOf(123.45);

    outputManager.handleResultPrinting(res, 3);

    assertEquals(StringConstants.RES_NO_FIT_IN_MANTISSA.getValue()
        + System.lineSeparator(), outContent.toString());

    System.setOut(originalOut);
  }

  @Test
  void handleResultPrinting_WhenCheckIsNot1Or2Or3() {
    OutputManager outputManager = new OutputManager();
    System.setOut(new java.io.PrintStream(outContent));

    BigDecimal res = BigDecimal.valueOf(123.45);

    outputManager.handleResultPrinting(res, 0);

    String expectedOutput = StringConstants.DOT_SEP.getValue() + System.lineSeparator() +
        StringConstants.DECIMAL_VALUE.getValue() + res.toPlainString() + System.lineSeparator() +
        "s21_decimal dec_res = {{0x3039, 0x0, 0x0, 0x20000}};" + System.lineSeparator();

    assertEquals(expectedOutput, outContent.toString());

    System.setOut(originalOut);
  }


  @Test
  void printDecimalValueFromBigDecimal() {
    OutputManager outputManager = new OutputManager();
    System.setOut(new java.io.PrintStream(outContent));

    BigDecimal bd = BigDecimal.valueOf(123.45);
    outputManager.printDecimalValueFromBigDecimal(bd);

    String expectedOutput = StringConstants.DECIMAL_VALUE.getValue() + System.lineSeparator() +
        bd.toPlainString() + System.lineSeparator();
    assertEquals(expectedOutput, outContent.toString());

    System.setOut(originalOut);
  }
}
