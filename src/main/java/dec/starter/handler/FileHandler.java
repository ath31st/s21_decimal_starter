package dec.starter.handler;

import static dec.starter.constant.FileHandlerConstants.CURRENT_WORKING_DIR;
import static dec.starter.constant.FileHandlerConstants.DELETE_FILE_UNSUCCESSFUL;
import static dec.starter.constant.FileHandlerConstants.DELETE_SUCCESS;
import static dec.starter.constant.FileHandlerConstants.DELETE_UNSUCCESSFUL;
import static dec.starter.constant.FileHandlerConstants.DIRECTORY_ABSENT;
import static dec.starter.constant.FileHandlerConstants.DIRECTORY_FOR_DELETE_ABSENT;
import static dec.starter.constant.FileHandlerConstants.FILE_C_EXT;
import static dec.starter.constant.FileHandlerConstants.FILE_TEST_PREFIX;
import static dec.starter.constant.FileHandlerConstants.MAC;
import static dec.starter.constant.FileHandlerConstants.NIX;
import static dec.starter.constant.FileHandlerConstants.NUX;
import static dec.starter.constant.FileHandlerConstants.OS_NAME;
import static dec.starter.constant.FileHandlerConstants.PATH_TO_SAVED_FILE;
import static dec.starter.constant.FileHandlerConstants.SAVE_SUCCESS;
import static dec.starter.constant.FileHandlerConstants.SAVE_UNSUCCESSFUL;
import static dec.starter.constant.FileHandlerConstants.UNIX_PATH_TO_TESTS;
import static dec.starter.constant.FileHandlerConstants.WIN;
import static dec.starter.constant.FileHandlerConstants.WIN_PATH_TO_TESTS;

import dec.starter.constant.FunctionNames;
import dec.starter.util.OutputManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Handles file-related operations such as saving generated tests to a file,
 * deleting files, and managing directories.
 */
public class FileHandler {
  /**
   * Gets the save directory.
   *
   * @return The save directory.
   */
  public String getSaveDirectory() {
    return saveDirectory;
  }

  private final String saveDirectory;
  private final OutputManager outputManager;

  /**
   * Constructs a FileHandler with the given OutputManager.
   *
   * @param outputManager The output manager for displaying messages.
   */
  public FileHandler(OutputManager outputManager) {
    this.outputManager = outputManager;
    String osName = System.getProperty(OS_NAME.getValue()).toLowerCase();

    if (osName.contains(WIN.getValue())) {
      saveDirectory = System.getProperty(CURRENT_WORKING_DIR.getValue())
          + WIN_PATH_TO_TESTS.getValue();
    } else if (osName.contains(NIX.getValue())
        || osName.contains(NUX.getValue())
        || osName.contains(MAC.getValue())) {
      saveDirectory = System.getProperty(CURRENT_WORKING_DIR.getValue())
          + UNIX_PATH_TO_TESTS.getValue();
    } else {
      saveDirectory = System.getProperty(CURRENT_WORKING_DIR.getValue());
    }
  }

  /**
   * Saves content to a file with the specified function name.
   *
   * @param funcName   The function name.
   * @param content The content to be saved.
   */
  public void saveContentToFile(FunctionNames funcName, String content) {
    File directory = new File(saveDirectory);
    boolean success = true;
    if (!directory.exists()) {
      success = directory.mkdir();
    }
    if (success) {
      String fileName = FILE_TEST_PREFIX.getValue() + funcName.getValue() + FILE_C_EXT.getValue();
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

  /**
   * Deletes files with generated tests and the directory.
   */
  public void deleteFilesAndDirectory() {
    File directory = new File(saveDirectory);

    if (directory.exists()) {
      File[] files = directory.listFiles();
      if (files != null) {
        deleteFiles(files);
      }
      try {
        Files.delete(Path.of(saveDirectory));
        outputManager.consolePrint(DELETE_SUCCESS.getValue());
      } catch (IOException e) {
        outputManager.consolePrint(DELETE_UNSUCCESSFUL.getValue());
      }
    } else {
      outputManager.consolePrint(DIRECTORY_FOR_DELETE_ABSENT.getValue());
    }
  }

  /**
   * Deletes an array of files.
   *
   * @param files The array of files to be deleted.
   */
  private void deleteFiles(File[] files) {
    Arrays.stream(files)
        .filter(File::isFile)
        .forEach(f -> {
          try {
            Files.delete(f.toPath());
          } catch (IOException e) {
            outputManager.consolePrint(DELETE_FILE_UNSUCCESSFUL.getValue());
          }
        });
  }
}
