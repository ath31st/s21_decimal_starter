package dec.gen;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Date;

public class BigDecimalGenerator {
  public static BigDecimal generateLimitedBigDecimal() {

    int length = generateRandomInteger(1, 29);

    StringBuilder numberBuilder = new StringBuilder();

    for (int i = 0; i < length; i++) {
      char digit = (char) (generateRandomInteger(0, 9) + '0');
      numberBuilder.append(digit);
    }

    if (generateRandomInteger(0, 1) == 0) {
      int commaIndex = generateRandomInteger(0, length);
      numberBuilder.insert(commaIndex, '.');
    }

    return new BigDecimal(numberBuilder.toString());
  }

  private static int generateRandomInteger(int min, int maxInclude) {
    SecureRandom r = new SecureRandom();
    r.setSeed(new Date().getTime());
    return r.nextInt((maxInclude - min) + 1) + min;
  }
}
