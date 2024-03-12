package dec.starter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dec.starter.constant.StringConstants;
import dec.starter.handler.ArithmeticHandler;
import dec.starter.handler.FileHandler;
import dec.starter.util.Converter;
import dec.starter.util.OutputManager;
import dec.starter.util.Validator;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProcessorTest {
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
  private Scanner scanner;
  @InjectMocks
  private Processor processor;

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final InputStream originalIn = System.in;

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  void restoreStreams() {
    System.setOut(originalOut);
    System.setIn(originalIn);
  }

//  @Test
//  void mainMenu_WhenUserChoosesOption1() {
//    ByteArrayInputStream in = new ByteArrayInputStream("1\nexit\n".getBytes());
//    System.setIn(in);
//
//    processor.mainMenu();
//
//    assertEquals(StringConstants.MAIN_MENU.getValue(), outContent.toString());
//  }

  @Test
  void arithmeticProcessing_WhenUserChoosesAction1() {
    ByteArrayInputStream in = new ByteArrayInputStream("5\nexit\n".getBytes());
    System.setIn(in);

    processor.arithmeticProcessing(new Scanner(System.in), 1);

    assertEquals(StringConstants.INPUT_TWO_NUMBERS.getValue()
        + System.lineSeparator(), outContent.toString());
  }
}
