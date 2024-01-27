package dec.starter.handler;

import static dec.starter.constant.FileHandlerConstants.*;

import dec.starter.util.OutputManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
  private final String userHomePath;
  private final OutputManager outputManager;

  public FileHandler(OutputManager outputManager) {
    this.outputManager = outputManager;
    String osName = System.getProperty(OS_NAME.getValue()).toLowerCase();

    if (osName.contains(WIN.getValue())) {
      userHomePath = System.getProperty(USER_HOME.getValue()) + WIN_PATH.getValue();
    } else if (osName.contains(NIX.getValue())
        || osName.contains(NUX.getValue())
        || osName.contains(MAC.getValue())) {
      userHomePath = System.getProperty(USER_HOME.getValue()) + UNIX_PATH.getValue();
    } else {
      userHomePath = System.getProperty(USER_DIR.getValue());
    }
  }

  public void saveContentToFile(String fileName, String content) {
    File directory = new File(userHomePath);
    boolean success = false;
    if (!directory.exists()) {
      success = directory.mkdir();
    }
    if (success) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(userHomePath + fileName))) {
        writer.write(content);
        outputManager.consolePrint(SAVE_SUCCESS.getValue());
      } catch (IOException e) {
        outputManager.consolePrint(SAVE_UNSUCCESSFUL.getValue()
            + e.getMessage());
      }
    }
  }
}

