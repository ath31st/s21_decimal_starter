package dec.starter.util;

import static dec.starter.constant.ArithmeticConstants.MAX_PRECISION;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Date;

/**
 * The BigDecimalGenerator class provides methods to generate random BigDecimal values
 * within specified limits and precision.
 *
 * <p>This class uses a secure random number generator to ensure randomness.</p>
 *
 * <p>Generated BigDecimals can have a limited precision defined by the MAX_PRECISION constant.</p>
 */
public class BigDecimalGenerator {
  private static final BigDecimal MAX_S21_DECIMAL_VALUE
      = new BigDecimal("79228162514264337593543950335");

  private BigDecimalGenerator() {
  }

  /**
   * Generates a random BigDecimal value within the specified precision limit.
   *
   * @return A randomly generated BigDecimal value.
   */
  public static BigDecimal generateLimitedBigDecimal() {

    int length = generateRandomInteger(1, MAX_PRECISION.getValue());

    StringBuilder numberBuilder = new StringBuilder();

    for (int i = 0; i < length; i++) {
      char digit = (char) (generateRandomInteger(0, 9) + '0');
      numberBuilder.append(digit);
    }

    if (generateRandomInteger(0, 1) == 0) {
      int commaIndex = generateRandomInteger(0, length);
      numberBuilder.insert(commaIndex, '.');
      if (commaIndex == 0 && length == MAX_PRECISION.getValue()) {
        numberBuilder.deleteCharAt(length - 1);
      }
    }

    BigDecimal bd = new BigDecimal(numberBuilder.toString());
    if (bd.compareTo(MAX_S21_DECIMAL_VALUE) > 0) {
      bd = MAX_S21_DECIMAL_VALUE.subtract(
          new BigDecimal(generateRandomInteger(1, Integer.MAX_VALUE)));
    }

    return generateRandomInteger(0, 1) == 0 ? bd : bd.negate();
  }

  /**
   * Generates a random integer within the specified range.
   *
   * @param min        The minimum value (inclusive) of the generated integer.
   * @param maxInclude The maximum value (inclusive) of the generated integer.
   * @return A randomly generated integer within the specified range.
   */
  private static int generateRandomInteger(int min, int maxInclude) {
    SecureRandom r = new SecureRandom();
    r.setSeed(new Date().getTime());
    return r.nextInt((maxInclude - min) + 1) + min;
  }
}
