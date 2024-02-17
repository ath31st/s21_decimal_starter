package dec.starter;

import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import dec.starter.handler.ArithmeticHandler;
import dec.starter.handler.FileHandler;
import dec.starter.util.Converter;
import dec.starter.util.OutputManager;
import dec.starter.util.TestBuilder;
import dec.starter.util.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class ProcessorTest {
  private Processor processor;
  @Mock
  private Validator validator;
  @Mock
  private Converter converter;
  @Mock
  private ArithmeticHandler arithmeticHandler;
  @Mock
  private OutputManager outputManager;
  @Mock
  private FileHandler fileHandler;
  @Mock
  private TestBuilder testBuilder;

  @BeforeEach
  void setUp() {
    processor = new Processor();
  }

  @Test
  void testMainMenu() throws Exception {
    String input = "0";
    withTextFromSystemIn(input)
        .execute(() -> assertDoesNotThrow(() -> processor.mainMenu()));
  }
}