package dec.starter.util;

import static dec.starter.constant.ArithmeticConstants.MAX_PRECISION;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import org.junit.jupiter.api.RepeatedTest;

class BigDecimalGeneratorTest {
  private static final BigDecimal MAX_S21_DECIMAL_VALUE
      = new BigDecimal("79228162514264337593543950335");

  @RepeatedTest(500)
  void testGenerateLimitedBigDecimalMultipleTimes() {
    BigDecimal generatedNumber = BigDecimalGenerator.generateLimitedBigDecimal();

    assertTrue(generatedNumber.precision() <= MAX_PRECISION.getValue());
    assertTrue(generatedNumber.abs().compareTo(MAX_S21_DECIMAL_VALUE) <= 0);
  }
}
