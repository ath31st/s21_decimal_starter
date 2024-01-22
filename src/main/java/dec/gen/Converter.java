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
    S21Decimal d = new S21Decimal();
    d.setScaleInBit(bd.scale());
    if (bd.compareTo(BigDecimal.ZERO) < 0) {
      d.setSignInBit(NEG_SIGN.getValue());
    }
    byte bytes[] = bd.unscaledValue().toByteArray();
    ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN);
    for (int i = 0; i < bytes.length; i++) {
      buffer.put(bytes[i]);
    }
    return d;
  }
}
