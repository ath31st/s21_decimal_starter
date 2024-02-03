package dec.starter.util;

import static dec.starter.constant.ArithmeticConstants.NEG_SIGN;

import dec.starter.model.S21Decimal;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Converter {
  public BigDecimal fromStrToDec(String strValue) {
    return new BigDecimal(strValue);
  }

  public S21Decimal fromDecToS21Dec(BigDecimal bd) {
    S21Decimal d = new S21Decimal();

    BigInteger unscaledValue = bd
        .unscaledValue()
        .abs();

    d.setScaleInBit(bd.scale());
    if (bd.signum() == -1) {
      d.setSignInBit(NEG_SIGN.getValue());
    }

    int[] intValues = splitTo32BitValues(unscaledValue);

    d.setLowBits(intValues[0]);
    d.setMidBits(intValues[1]);
    d.setHighBits(intValues[2]);

    return d;
  }

  private int[] splitTo32BitValues(BigInteger value) {
    int[] intValues = new int[3];

    byte[] bytes = value.toByteArray();
    int length = bytes.length;

    for (int i = 0; i < 3; i++) {
      int offset = i * 4;
      int intValue = 0;

      for (int j = 0; j < 4; j++) {
        if (offset + j < length) {
          intValue |= (bytes[length - offset - j - 1] & 0xFF) << (j * 8);
        }
      }

      intValues[i] = intValue;
    }

    return intValues;
  }
}
