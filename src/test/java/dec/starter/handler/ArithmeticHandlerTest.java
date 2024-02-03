package dec.starter.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dec.starter.constant.FunctionNames;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArithmeticHandlerTest {

  private ArithmeticHandler arithmeticHandler;

  @BeforeEach
  void setUp() {
    arithmeticHandler = new ArithmeticHandler();
  }

  @Test
  void testAdd() {
    BigDecimal result = arithmeticHandler.add(new BigDecimal("2.5"), new BigDecimal("3.5"));
    assertEquals(new BigDecimal("6.0"), result);
  }

  @Test
  void testSub() {
    BigDecimal result = arithmeticHandler.sub(new BigDecimal("5.0"), new BigDecimal("2.0"));
    assertEquals(new BigDecimal("3.0"), result);
  }

  @Test
  void testMul() {
    BigDecimal result = arithmeticHandler.mul(new BigDecimal("2.5"), new BigDecimal("2"));
    assertEquals(new BigDecimal("5.0"), result);
  }

  @Test
  void testDiv() {
    BigDecimal result = arithmeticHandler.div(new BigDecimal("6.0"), new BigDecimal("2.0"));
    assertEquals(new BigDecimal("3"), result);
  }

  @Test
  void testDivByZero() {
    assertThrows(IllegalArgumentException.class, () ->
        arithmeticHandler.div(new BigDecimal("5.0"), BigDecimal.ZERO));
  }

  @Test
  void testAdditionalRounding() {
    BigDecimal result = arithmeticHandler.additionalRounding(
        new BigDecimal("0.79228162514264337593543950335"));
    assertEquals(new BigDecimal("0.7922816251426433759354395034"), result);
  }

  @Test
  void testNoAdditionalRounding1() {
    BigDecimal result = arithmeticHandler.additionalRounding(
        new BigDecimal("1.79228162514264337593543950335"));
    assertEquals(new BigDecimal("1.79228162514264337593543950335"), result);
  }

  @Test
  void testNoAdditionalRounding2() {
    BigDecimal result = arithmeticHandler.additionalRounding(
        new BigDecimal("1.792281625142643375935439503"));
    assertEquals(new BigDecimal("1.792281625142643375935439503"), result);
  }

  @Test
  void testNoAdditionalRounding3() {
    BigDecimal result = arithmeticHandler.additionalRounding(
        new BigDecimal("0.7922816251426433759354395033555"));
    assertEquals(new BigDecimal("0.7922816251426433759354395033555"), result);
  }

  @Test
  void testCalculateResultWithActionAdd() {
    BigDecimal result = arithmeticHandler.calculateResult(
        new BigDecimal("4.0"), new BigDecimal("2.0"), 1);
    assertEquals(new BigDecimal("6.0"), result);
  }

  @Test
  void testCalculateResultWithActionSub() {
    BigDecimal result = arithmeticHandler.calculateResult(
        new BigDecimal("321.0"), new BigDecimal("2.33"), 2);
    assertEquals(new BigDecimal("318.67"), result);
  }

  @Test
  void testCalculateResultWithActionMul() {
    BigDecimal result = arithmeticHandler.calculateResult(
        new BigDecimal("111.1"), new BigDecimal("3"), 3);
    assertEquals(new BigDecimal("333.3"), result);
  }

  @Test
  void testCalculateResultWithActionDiv() {
    BigDecimal result = arithmeticHandler.calculateResult(
        new BigDecimal("1000"), new BigDecimal("4"), 4);
    assertEquals(new BigDecimal("250"), result);
  }

  @Test
  void testCalculateResultWithActionDefault() {
    BigDecimal result = arithmeticHandler.calculateResult(
        new BigDecimal("1342445"), new BigDecimal("456.435"), 5);
    assertEquals(BigDecimal.ZERO, result);
  }


  @Test
  void testCalculateResultWithFunctionNamesAdd() {
    BigDecimal result = arithmeticHandler.calculateResult(
        new BigDecimal("4.0"), new BigDecimal("2.0"), FunctionNames.S21_ADD);
    assertEquals(new BigDecimal("6.0"), result);
  }

  @Test
  void testCalculateResultWithFunctionNamesSub() {
    BigDecimal result = arithmeticHandler.calculateResult(
        new BigDecimal("321.0"), new BigDecimal("2.33"), FunctionNames.S21_SUB);
    assertEquals(new BigDecimal("318.67"), result);
  }

  @Test
  void testCalculateResultWithFunctionNamesMul() {
    BigDecimal result = arithmeticHandler.calculateResult(
        new BigDecimal("111.1"), new BigDecimal("3"), FunctionNames.S21_MUL);
    assertEquals(new BigDecimal("333.3"), result);
  }

  @Test
  void testCalculateResultWithFunctionNamesDiv() {
    BigDecimal result = arithmeticHandler.calculateResult(
        new BigDecimal("1000"), new BigDecimal("4"), FunctionNames.S21_DIV);
    assertEquals(new BigDecimal("250"), result);
  }

  @Test
  void testCalculateResultWithFunctionNamesDefault() {
    BigDecimal result = arithmeticHandler.calculateResult(
        new BigDecimal("1342445"), new BigDecimal("456.435"), FunctionNames.ALL_FUNCTIONS);
    assertEquals(BigDecimal.ZERO, result);
  }
}
