package dec.starter;

import static dec.starter.constant.StringConstants.HB_LOGO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
  private static final Logger logger = LogManager.getLogger(App.class);

  public static void main(String[] args) {
    if (args.length > 0 && args[0].equals("-hb")) {
      logger.info(HB_LOGO.getValue());
    }
    Processor processor = new Processor();
    processor.mainMenu();
  }
}
