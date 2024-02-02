package dec.starter.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ValidatorTest {

  @Test
  void testValidBigDecimal() {
    Validator validator = new Validator();
    BigDecimal validBigDecimal = new BigDecimal("123.45");
    int result = validator.checkBigDecimal(validBigDecimal);
    assertEquals(0, result);
  }

  @Test
  void testBigDecimalTooSmall() {
    Validator validator = new Validator();
    BigDecimal tooSmallBigDecimal = new BigDecimal("-79228162514264337593543950336");
    int result = validator.checkBigDecimal(tooSmallBigDecimal);
    assertEquals(2, result);
  }

  @Test
  void testBigDecimalTooLarge() {
    Validator validator = new Validator();
    BigDecimal tooLargeBigDecimal = new BigDecimal("79228162514264337593543950336");
    int result = validator.checkBigDecimal(tooLargeBigDecimal);
    assertEquals(1, result);
  }

  @Test
  void testValidMaxS21Decimal() {
    Validator validator = new Validator();
    BigDecimal maxS21Decimal = new BigDecimal("79228162514264337593543950335");
    int result = validator.checkBigDecimal(maxS21Decimal);
    assertEquals(0, result);
  }

  @Test
  void testValidMinS21Decimal() {
    Validator validator = new Validator();
    BigDecimal minS21Decimal = new BigDecimal("-79228162514264337593543950335");
    int result = validator.checkBigDecimal(minS21Decimal);
    assertEquals(0, result);
  }

  @Test
  void testBigScaleS21Decimal() {
    Validator validator = new Validator();
    BigDecimal bigScaleS21Decimal = new BigDecimal("7.9228162514264337593543950335");
    int result = validator.checkBigDecimal(bigScaleS21Decimal);
    assertEquals(0, result);
  }

  @Test
  void testInvalidBigScaleS21Decimal() {
    Validator validator = new Validator();
    BigDecimal bigScaleS21Decimal = new BigDecimal("0.79228162514264337593543950335");
    int result = validator.checkBigDecimal(bigScaleS21Decimal);
    assertEquals(2, result);
  }

  @Test
  void testInvalidVaxPrecisionS21Decimal() {
    Validator validator = new Validator();
    BigDecimal bigPrecisionS21Decimal = new BigDecimal("7922816251426433759354395033599");
    int result = validator.checkBigDecimal(bigPrecisionS21Decimal);
    assertEquals(1, result);
  }
}
