package dec.starter;

import static dec.starter.constant.StringConstants.HB_LOGO;

import dec.starter.util.OutputManager;

public class App {
  public static void main(String[] args) {
    OutputManager outputManager = new OutputManager();
    Processor processor = new Processor();
    outputManager.consolePrint(HB_LOGO.getValue());
    processor.mainMenu();
  }
}
