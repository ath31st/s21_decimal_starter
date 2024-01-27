package dec.starter.handler;

import static dec.starter.constant.FileHandlerConstants.DELETE_SUCCESS;
import static dec.starter.constant.FileHandlerConstants.DELETE_UNSUCCESSFUL;
import static dec.starter.constant.FileHandlerConstants.DIRECTORY_ABSENT;
import static dec.starter.constant.FileHandlerConstants.DOCS;
import static dec.starter.constant.FileHandlerConstants.FILE_C_EXT;
import static dec.starter.constant.FileHandlerConstants.FILE_TEST_PREFIX;
import static dec.starter.constant.FileHandlerConstants.MAC;
import static dec.starter.constant.FileHandlerConstants.NIX;
import static dec.starter.constant.FileHandlerConstants.NUX;
import static dec.starter.constant.FileHandlerConstants.OS_NAME;
import static dec.starter.constant.FileHandlerConstants.PATH_TO_SAVED_FILE;
import static dec.starter.constant.FileHandlerConstants.SAVE_SUCCESS;
import static dec.starter.constant.FileHandlerConstants.SAVE_UNSUCCESSFUL;
import static dec.starter.constant.FileHandlerConstants.UNIX_PATH;
import static dec.starter.constant.FileHandlerConstants.UNIX_PATH_RU;
import static dec.starter.constant.FileHandlerConstants.USER_DIR;
import static dec.starter.constant.FileHandlerConstants.USER_HOME;
import static dec.starter.constant.FileHandlerConstants.WIN;
import static dec.starter.constant.FileHandlerConstants.WIN_PATH;

import dec.starter.constant.FunctionNames;
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
      saveDirectory = getLinuxDocumentsFolder();
    } else {
      saveDirectory = System.getProperty(USER_DIR.getValue());
    }
  }

  private String getLinuxDocumentsFolder() {
    File documentsFolder = new File(System.getProperty(USER_HOME.getValue()), DOCS.getValue());
    String userHomePath;
    if (documentsFolder.exists() && documentsFolder.isDirectory()) {
      userHomePath = System.getProperty(USER_HOME.getValue()) + UNIX_PATH_RU.getValue();
    } else {
      userHomePath = System.getProperty(USER_HOME.getValue() + UNIX_PATH.getValue());
    }
    return userHomePath;
  }

  public void saveContentToFile(FunctionNames fName, String content) {
    File directory = new File(saveDirectory);
    boolean success = true;
    if (!directory.exists()) {
      success = directory.mkdir();
    }
    if (success) {
      String fileName = FILE_TEST_PREFIX.getValue() + fName.getValue() + FILE_C_EXT.getValue();
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveDirectory + fileName))) {
        writer.write(content);
        outputManager.consolePrint(SAVE_SUCCESS.getValue());
        outputManager.consolePrint(PATH_TO_SAVED_FILE.getValue() + saveDirectory + fileName);
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
