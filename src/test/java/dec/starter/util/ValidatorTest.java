package dec.starter.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ValidatorTest {
  private final Validator validator = new Validator();

  @Test
  void testValidBigDecimal() {
    BigDecimal validBigDecimal = new BigDecimal("123.45");
    int result = validator.checkBigDecimal(validBigDecimal);
    assertEquals(0, result);
  }

  @Test
  void testBigDecimalTooSmall() {
    BigDecimal tooSmallBigDecimal = new BigDecimal("-79228162514264337593543950336");
    int result = validator.checkBigDecimal(tooSmallBigDecimal);
    assertEquals(2, result);
  }

  @Test
  void testBigDecimalTooLarge1() {
    BigDecimal tooLargeBigDecimal = new BigDecimal("79228162514264337593543950336");
    int result = validator.checkBigDecimal(tooLargeBigDecimal);
    assertEquals(1, result);
  }

  @Test
  void testBigDecimalTooLarge2() {
    BigDecimal maxS21Decimal = new BigDecimal("7922816251426433759354395033.6");
    int result = validator.checkBigDecimal(maxS21Decimal);
    assertEquals(3, result);
  }

  @Test
  void testValidMaxS21Decimal() {
    BigDecimal maxS21Decimal = new BigDecimal("79228162514264337593543950335");
    int result = validator.checkBigDecimal(maxS21Decimal);
    assertEquals(0, result);
  }

  @Test
  void testValidMinS21Decimal() {
    BigDecimal minS21Decimal = new BigDecimal("-79228162514264337593543950335");
    int result = validator.checkBigDecimal(minS21Decimal);
    assertEquals(0, result);
  }

  @Test
  void testBigScaleS21Decimal() {
    BigDecimal bigScaleS21Decimal = new BigDecimal("7.9228162514264337593543950335");
    int result = validator.checkBigDecimal(bigScaleS21Decimal);
    assertEquals(0, result);
  }

  @Test
  void testInvalidBigScaleS21Decimal() {
    BigDecimal bigScaleS21Decimal = new BigDecimal("0.79228162514264337593543950335");
    int result = validator.checkBigDecimal(bigScaleS21Decimal);
    assertEquals(2, result);
  }

  @Test
  void testInvalidVaxPrecisionS21Decimal() {
    BigDecimal bigPrecisionS21Decimal = new BigDecimal("7922816251426433759354395033599");
    int result = validator.checkBigDecimal(bigPrecisionS21Decimal);
    assertEquals(1, result);
  }

  @Test
  void testCheckStrS21decimalThrowException1() {
    String strVal = "s21_decimal dec_res = {{0xfffff, 0xffffff, 0xffffffff, 0xffffffff, 0x20000}};";
    assertThrows(IllegalArgumentException.class, () -> validator.checkStrS21decimal(strVal));
  }

  @Test
  void testCheckStrS21decimalThrowException2() {
    String strVal = "s21_decimal dec_res = {{0xffffffff, 0xffffffff, 0x20000}};";
    assertThrows(IllegalArgumentException.class, () -> validator.checkStrS21decimal(strVal));
  }

  @Test
  void testCheckStrS21decimalSuccess() {
    String strVal = "s21_decimal dec_res = {{0xffffff, 0xffffffff, 0xffffffff, 0x20000}};";
    assertDoesNotThrow(() -> validator.checkStrS21decimal(strVal));
  }

  @Test
  void testCheckSignScaleBitsSuccess() {
    int signScaleBits = Integer.parseUnsignedInt("10000", 16);
    assertDoesNotThrow(() -> validator.checkSignScaleBits(signScaleBits));
  }

  @Test
  void testCheckSignScaleBitsThrowException1() {
    int signScaleBits = Integer.parseUnsignedInt("1D0000", 16);
    assertThrows(IllegalArgumentException.class, () -> validator.checkSignScaleBits(signScaleBits));
  }

  @Test
  void testCheckSignScaleBitsThrowException2() {
    int signScaleBits = Integer.parseUnsignedInt("1C0001", 16);
    assertThrows(IllegalArgumentException.class, () -> validator.checkSignScaleBits(signScaleBits));
  }

  @Test
  void testCheckSignScaleBitsThrowException3() {
    int signScaleBits = Integer.parseUnsignedInt("380000", 16);
    assertThrows(IllegalArgumentException.class, () -> validator.checkSignScaleBits(signScaleBits));
  }

  @Test
  void testCheckSignScaleBitsThrowException4() {
    int signScaleBits = Integer.parseUnsignedInt("1C8000", 16);
    assertThrows(IllegalArgumentException.class, () -> validator.checkSignScaleBits(signScaleBits));
  }

  @Test
  void testCheckSignScaleBitsThrowException5() {
    int signScaleBits = Integer.parseUnsignedInt("11C0000", 16);
    assertThrows(IllegalArgumentException.class, () -> validator.checkSignScaleBits(signScaleBits));
  }

  @Test
  void testCheckSignScaleBitsThrowException6() {
    int signScaleBits = Integer.parseUnsignedInt("401C0000", 16);
    assertThrows(IllegalArgumentException.class, () -> validator.checkSignScaleBits(signScaleBits));
  }

  @Test
  void testCheckSignScaleBitsThrowException7() {
    int signScaleBits = Integer.parseUnsignedInt("FFFFFFFF", 16);
    assertThrows(IllegalArgumentException.class, () -> validator.checkSignScaleBits(signScaleBits));
  }
}
