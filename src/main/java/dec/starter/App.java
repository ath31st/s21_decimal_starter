package dec.starter;

import static dec.starter.constant.StringConstants.HB_LOGO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
  private static final Logger logger = LogManager.getLogger(App.class);

  public static void main(String[] args) {
    Processor processor = new Processor();
    logger.info(HB_LOGO.getValue());
    processor.mainMenu();
  }
}
