package dec.starter.handler;

import static dec.starter.constant.FileHandlerConstants.*;

import dec.starter.util.OutputManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler {
  private final String saveDirectory;
  private final OutputManager outputManager;

  public FileHandler(OutputManager outputManager) {
    this.outputManager = outputManager;
    String osName = System.getProperty(OS_NAME.getValue()).toLowerCase();

    if (osName.contains(WIN.getValue())) {
      saveDirectory = System.getProperty(USER_HOME.getValue()) + WIN_PATH.getValue();
    } else if (osName.contains(NIX.getValue())
        || osName.contains(NUX.getValue())
        || osName.contains(MAC.getValue())) {
      saveDirectory = System.getProperty(USER_HOME.getValue()) + UNIX_PATH.getValue();
    } else {
      saveDirectory = System.getProperty(USER_DIR.getValue());
    }
  }

  public void saveContentToFile(String fileName, String content) {
    File directory = new File(saveDirectory);
    boolean success = false;
    if (!directory.exists()) {
      success = directory.mkdir();
    }
    if (success) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveDirectory + fileName))) {
        writer.write(content);
        outputManager.consolePrint(SAVE_SUCCESS.getValue());
      } catch (IOException e) {
        outputManager.consolePrint(SAVE_UNSUCCESSFUL.getValue()
            + e.getMessage());
      }
    } else {
      outputManager.consolePrint(DIRECTORY_ABSENT.getValue());
    }
  }

  public void deleteFilesAndDirectory() {
    File directory = new File(saveDirectory);

    if (directory.exists()) {
      File[] files = directory.listFiles();

      if (files != null) {
        for (File file : files) {
          if (file.isFile()) {
            file.delete();
          }
        }
      }
      try {
        Files.delete(Path.of(saveDirectory));
        outputManager.consolePrint(DELETE_SUCCESS.getValue());
      } catch (IOException e) {
        outputManager.consolePrint(DELETE_UNSUCCESSFUL.getValue());
      }
    } else {
      outputManager.consolePrint(DIRECTORY_ABSENT.getValue());
    }
  }
}
