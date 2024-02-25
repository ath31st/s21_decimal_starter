package dec.starter.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dec.starter.model.S21Decimal;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;


class ConverterTest {

  private final Converter converter = new Converter();

  @Test
  void testFromStrToDec() {
    String strValue = "123.456";
    BigDecimal result = converter.fromStrToDec(strValue);
    assertEquals(new BigDecimal(strValue), result);
  }

  @Test
  void testFromDecToS21DecPositive() {
    BigDecimal bd = new BigDecimal("123.456");
    S21Decimal result = converter.fromDecToS21Dec(bd);

    assertEquals(0x1e240, result.getLowBits());
    assertEquals(0x0, result.getMidBits());
    assertEquals(0x0, result.getHighBits());
    assertEquals(0x30000, result.getSignScaleBits());
  }

  @Test
  void testFromDecToS21DecNegative() {
    BigDecimal bd = new BigDecimal("-123.456");
    S21Decimal result = converter.fromDecToS21Dec(bd);

    assertEquals(0x1e240, result.getLowBits());
    assertEquals(0x0, result.getMidBits());
    assertEquals(0x0, result.getHighBits());
    assertEquals(0x80030000, result.getSignScaleBits());
  }

  @Test
  void testFromDecToS21DecZero() {
    BigDecimal bd = BigDecimal.ZERO;
    S21Decimal result = converter.fromDecToS21Dec(bd);

    assertEquals(0, result.getHighBits());
    assertEquals(0, result.getMidBits());
    assertEquals(0, result.getLowBits());
    assertEquals(0, result.getSignScaleBits());
  }

  @Test
  void testFromDecToS21DecLargeValue() {
    BigDecimal bd = new BigDecimal("12345678901234567890.123456789");
    S21Decimal result = converter.fromDecToS21Dec(bd);

    assertEquals(0x6e398115, result.getLowBits());
    assertEquals(0x46bec9b1, result.getMidBits());
    assertEquals(0x27e41b32, result.getHighBits());
    assertEquals(0x90000, result.getSignScaleBits());
  }

  @Test
  void testFromStrToS21DecSuccess1() {
    String strVal = "s21_decimal dec_res = {{0xa80af531, 0x4e7c160f, 0x2d26, 0xe0000}};";
    S21Decimal checkDec = new S21Decimal();
    checkDec.setLowBits(Integer.parseUnsignedInt("0xa80af531".substring(2), 16));
    checkDec.setMidBits(Integer.parseUnsignedInt("0x4e7c160f".substring(2), 16));
    checkDec.setHighBits(Integer.parseUnsignedInt("0x2d26".substring(2), 16));
    checkDec.setSignScaleBits(Integer.parseUnsignedInt("0xe0000".substring(2), 16));

    S21Decimal result = converter.fromStrToS21Dec(strVal);

    assertEquals(checkDec.getLowBits(), result.getLowBits());
    assertEquals(checkDec.getMidBits(), result.getMidBits());
    assertEquals(checkDec.getHighBits(), result.getHighBits());
    assertEquals(checkDec.getSignScaleBits(), result.getSignScaleBits());
  }

  @Test
  void testFromStrToS21DecSuccessZero() {
    String strVal = "s21_decimal dec_res = {{0x0, 0x0, 0x0, 0x0}};";
    S21Decimal checkDec = new S21Decimal();
    checkDec.setLowBits(Integer.parseUnsignedInt("0x0".substring(2), 16));
    checkDec.setMidBits(Integer.parseUnsignedInt("0x0".substring(2), 16));
    checkDec.setHighBits(Integer.parseUnsignedInt("0x0".substring(2), 16));
    checkDec.setSignScaleBits(Integer.parseUnsignedInt("0x0".substring(2), 16));

    S21Decimal result = converter.fromStrToS21Dec(strVal);

    assertEquals(checkDec.getLowBits(), result.getLowBits());
    assertEquals(checkDec.getMidBits(), result.getMidBits());
    assertEquals(checkDec.getHighBits(), result.getHighBits());
    assertEquals(checkDec.getSignScaleBits(), result.getSignScaleBits());
  }

  @Test
  void testFromStrToS21DecSuccess2() {
    String strVal = "s21_decimal dec_res = {{0xe93e4e02, 0xb7acc82, 0xd3c2, 0x800c0000}};";
    S21Decimal checkDec = new S21Decimal();
    checkDec.setLowBits(Integer.parseUnsignedInt("0xe93e4e02".substring(2), 16));
    checkDec.setMidBits(Integer.parseUnsignedInt("0xb7acc82".substring(2), 16));
    checkDec.setHighBits(Integer.parseUnsignedInt("0xd3c2".substring(2), 16));
    checkDec.setSignScaleBits(Integer.parseUnsignedInt("0x800c0000".substring(2), 16));

    S21Decimal result = converter.fromStrToS21Dec(strVal);

    assertEquals(checkDec.getLowBits(), result.getLowBits());
    assertEquals(checkDec.getMidBits(), result.getMidBits());
    assertEquals(checkDec.getHighBits(), result.getHighBits());
    assertEquals(checkDec.getSignScaleBits(), result.getSignScaleBits());
  }

  @Test
  void testFromStrToS21DecSuccess3() {
    String strVal = "s21_decimal dec_res = {{0xd5, 0x0, 0x0, 0x80120000}};";
    S21Decimal checkDec = new S21Decimal();
    checkDec.setLowBits(Integer.parseUnsignedInt("0xd5".substring(2), 16));
    checkDec.setMidBits(Integer.parseUnsignedInt("0x0".substring(2), 16));
    checkDec.setHighBits(Integer.parseUnsignedInt("0x0".substring(2), 16));
    checkDec.setSignScaleBits(Integer.parseUnsignedInt("0x80120000".substring(2), 16));

    S21Decimal result = converter.fromStrToS21Dec(strVal);

    assertEquals(checkDec.getLowBits(), result.getLowBits());
    assertEquals(checkDec.getMidBits(), result.getMidBits());
    assertEquals(checkDec.getHighBits(), result.getHighBits());
    assertEquals(checkDec.getSignScaleBits(), result.getSignScaleBits());
  }

  @Test
  void testFromStrToS21DecSuccess4() {
    String strVal = "s21_decimal dec_res = {{0xffffffff, 0xffffffff, 0xffffffff, 0x0}};";
    S21Decimal checkDec = new S21Decimal();
    checkDec.setLowBits(Integer.parseUnsignedInt("0xffffffff".substring(2), 16));
    checkDec.setMidBits(Integer.parseUnsignedInt("0xffffffff".substring(2), 16));
    checkDec.setHighBits(Integer.parseUnsignedInt("0xffffffff".substring(2), 16));
    checkDec.setSignScaleBits(Integer.parseUnsignedInt("0x0".substring(2), 16));

    S21Decimal result = converter.fromStrToS21Dec(strVal);

    assertEquals(checkDec.getLowBits(), result.getLowBits());
    assertEquals(checkDec.getMidBits(), result.getMidBits());
    assertEquals(checkDec.getHighBits(), result.getHighBits());
    assertEquals(checkDec.getSignScaleBits(), result.getSignScaleBits());
  }

  @Test
  void testFromStrToS21DecSuccess5() {
    String strVal = "s21_decimal dec_res = {{0xffffffff, 0xffffffff, 0xffffffff, 0x80000000}};";
    S21Decimal checkDec = new S21Decimal();
    checkDec.setLowBits(Integer.parseUnsignedInt("0xffffffff".substring(2), 16));
    checkDec.setMidBits(Integer.parseUnsignedInt("0xffffffff".substring(2), 16));
    checkDec.setHighBits(Integer.parseUnsignedInt("0xffffffff".substring(2), 16));
    checkDec.setSignScaleBits(Integer.parseUnsignedInt("0x80000000".substring(2), 16));

    S21Decimal result = converter.fromStrToS21Dec(strVal);

    assertEquals(checkDec.getLowBits(), result.getLowBits());
    assertEquals(checkDec.getMidBits(), result.getMidBits());
    assertEquals(checkDec.getHighBits(), result.getHighBits());
    assertEquals(checkDec.getSignScaleBits(), result.getSignScaleBits());
  }

  @Test
  void testFromS21DecToDecSuccess1() {
    String strVal = "s21_decimal dec_res = {{0xffffffff, 0xffffffff, 0xffffffff, 0x80000000}};";
    S21Decimal s21Dec = converter.fromStrToS21Dec(strVal);
    BigDecimal minS21Decimal = new BigDecimal("-79228162514264337593543950335");

    BigDecimal result = converter.fromS21DecToDec(s21Dec);

    assertEquals(0, result.compareTo(minS21Decimal));
  }

  @Test
  void testFromS21DecToDecSuccess2() {
    String strVal = "s21_decimal dec_res = {{0xffffffff, 0xffffffff, 0xffffffff, 0x0}};";
    S21Decimal s21Dec = converter.fromStrToS21Dec(strVal);
    BigDecimal maxS21Decimal = new BigDecimal("79228162514264337593543950335");

    BigDecimal result = converter.fromS21DecToDec(s21Dec);

    assertEquals(0, result.compareTo(maxS21Decimal));
  }

  @Test
  void testFromS21DecToDecSuccessZero() {
    String strVal = "s21_decimal dec_res = {{0x0, 0x0, 0x0, 0x0}};";
    S21Decimal s21Dec = converter.fromStrToS21Dec(strVal);
    BigDecimal bd = BigDecimal.ZERO;

    BigDecimal result = converter.fromS21DecToDec(s21Dec);

    assertEquals(0, result.compareTo(bd));
  }

  @Test
  void testFromS21DecToDecSuccess3() {
    String strVal = "s21_decimal dec_res = {{0x1, 0x0, 0x0, 0x0}};";
    S21Decimal s21Dec = converter.fromStrToS21Dec(strVal);
    BigDecimal bd = BigDecimal.ONE;

    BigDecimal result = converter.fromS21DecToDec(s21Dec);

    assertEquals(0, result.compareTo(bd));
  }

  @Test
  void testFromS21DecToDecSuccess4() {
    String strVal = "s21_decimal dec_res = {{0xcaed7ac9, 0x849ba54f, 0x31a8, 0x70000}};";
    S21Decimal s21Dec = converter.fromStrToS21Dec(strVal);
    BigDecimal bd = new BigDecimal("23450456607779234.2342345");

    BigDecimal result = converter.fromS21DecToDec(s21Dec);

    assertEquals(0, result.compareTo(bd));
  }

  @Test
  void testFromS21DecToDecSuccess5() {
    String strVal = "s21_decimal dec_res = {{0xffffffff, 0xffffffff, 0xffffffff, 0x20000}};";
    S21Decimal s21Dec = converter.fromStrToS21Dec(strVal);
    BigDecimal bd = new BigDecimal("792281625142643375935439503.35");

    BigDecimal result = converter.fromS21DecToDec(s21Dec);

    assertEquals(0, result.compareTo(bd));
  }

  @Test
  void testFromS21DecToDecThrowException1() {
    String strVal = "s21_decimal dec_res = {{0xffffffff, 0xffffffff, 0x20000}};";
    assertThrows(IllegalArgumentException.class, () -> converter.fromStrToS21Dec(strVal));
  }

  @Test
  void testFromS21DecToDecThrowException2() {
    String strVal = "s21_decimal dec_res = {{}};";
    assertThrows(IllegalArgumentException.class, () -> converter.fromStrToS21Dec(strVal));
  }
}
