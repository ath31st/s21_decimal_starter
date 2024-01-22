package dec.gen;

import static dec.gen.Constants.NEG_SIGN;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Converter {
  public BigDecimal fromStrToDec(String strValue) {
    return new BigDecimal(strValue);
  }

  public String fromDecToStr(BigDecimal bd) {
    return bd.toPlainString();
  }

  public S21Decimal fromDecToS21Dec(BigDecimal bd) {
    int offset;
    if (bd.unscaledValue().bitLength() < 45) {
      offset = 0;
    } else {
      offset = 1;
    }

    S21Decimal d = new S21Decimal();
    d.setScaleInBit(bd.scale());
    if (bd.signum() == -1) {
      d.setSignInBit(NEG_SIGN.getValue());
    }

    byte[] bytes = bd.unscaledValue().toByteArray();
    ByteBuffer buffer = ByteBuffer.allocate(12).order(ByteOrder.BIG_ENDIAN);

    for (int i = offset; i < 12 + offset; i++) {
      if (i < bytes.length) {
        buffer.put(bytes[i]);
      }
    }

    d.setLowBits(buffer.getInt(0));
    d.setMidBits(buffer.getInt(4));
    d.setHighBits(buffer.getInt(8));
    //79228162514264337593543950335
    return d;
  }
}
