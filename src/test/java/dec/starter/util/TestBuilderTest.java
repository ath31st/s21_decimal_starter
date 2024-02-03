package dec.starter.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import dec.starter.constant.FunctionNames;
import dec.starter.handler.ArithmeticHandler;
import dec.starter.model.S21Decimal;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class TestBuilderTest {
  private TestBuilder testBuilder;
  @Mock
  private Validator validator;
  @Mock
  private Converter converter;
  @Mock
  private ArithmeticHandler arithmeticHandler;

  @BeforeEach
  void setUp() {
    validator = mock(Validator.class);
    converter = mock(Converter.class);
    arithmeticHandler = mock(ArithmeticHandler.class);
    testBuilder = new TestBuilder(validator, converter, arithmeticHandler);
  }

  @Test
  void testBuildTestSuitWithoutFailTest() {
    FunctionNames fName = FunctionNames.S21_ADD;
    int count = 2;

    BigDecimal expectedResult = BigDecimal.ONE;

    when(validator.checkBigDecimal(any(BigDecimal.class))).thenReturn(0);
    when(converter.fromDecToS21Dec(any(BigDecimal.class))).thenReturn(new S21Decimal());
    when(arithmeticHandler.calculateResult(any(BigDecimal.class), any(BigDecimal.class), eq(fName)))
        .thenReturn(expectedResult);

    String result = testBuilder.buildTestSuit(fName, count);

    assertNotNull(result);
  }


  @Test
  void testBuildAllSuitsAtOnce() {
    int count = 2;

    BigDecimal expectedResult = BigDecimal.ONE;

    when(validator.checkBigDecimal(any(BigDecimal.class))).thenReturn(0);
    when(converter.fromDecToS21Dec(any(BigDecimal.class))).thenReturn(new S21Decimal());
    when(arithmeticHandler.calculateResult(any(BigDecimal.class), any(BigDecimal.class), any(FunctionNames.class)))
        .thenReturn(expectedResult);

    String result = testBuilder.buildAllSuitsAtOnce(count);

    assertNotNull(result);
  }
}