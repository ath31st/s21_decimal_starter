package dec.starter.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
  void testFromStrToS21Dec() {
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
}
