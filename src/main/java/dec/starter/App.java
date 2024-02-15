package dec.starter;

import static dec.starter.constant.StringConstants.HB_LOGO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The main class of the application.
 * This class contains the main method and serves as the entry point for the program.
 */
public class App {
  private static final Logger logger = LogManager.getLogger(App.class);

  /**
   * The main method of the application.
   * It checks command-line arguments and executes the main menu of the Processor class.
   *
   * @param args Command-line arguments provided to the program.
   */
  public static void main(String[] args) {
    if (args.length > 0 && args[0].equals("-hb")) {
      logger.info(HB_LOGO.getValue());
    }
    Processor processor = new Processor();
    processor.mainMenu();
  }
}
