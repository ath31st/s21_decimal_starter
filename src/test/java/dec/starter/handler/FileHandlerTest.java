package dec.starter.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dec.starter.constant.FileHandlerConstants;
import dec.starter.constant.FunctionNames;
import dec.starter.util.OutputManager;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileHandlerTest {

  private FileHandler fileHandler;
  private OutputManagerStub outputManager;

  @BeforeEach
  void setUp() {
    outputManager = new OutputManagerStub();
    fileHandler = new FileHandler(outputManager);
  }

  @AfterEach
  void cleanUp() {
    fileHandler.deleteFilesAndDirectory();
  }

  @Test
  void testSaveContentToFile() throws IOException {
    String content = "Test content";
    FunctionNames functionName = FunctionNames.S21_ADD;

    fileHandler.saveContentToFile(functionName, content);

    String expectedFileName = "test_s21_add.c";
    Path expectedFilePath = Path.of(fileHandler.getSaveDirectory(), expectedFileName);

    assertTrue(Files.exists(expectedFilePath));
    assertEquals(content, readContentFromFile(expectedFilePath));
    assertTrue(outputManager.getConsoleOutput().contains(FileHandlerConstants.SAVE_SUCCESS.getValue()));
    assertTrue(outputManager.getConsoleOutput().contains(FileHandlerConstants.PATH_TO_SAVED_FILE.getValue() + expectedFilePath));
  }

  @Test
  void testDeleteFilesAndDirectory() {
    String content = "Test content";
    FunctionNames functionName = FunctionNames.S21_ADD;

    fileHandler.saveContentToFile(functionName, content);
    fileHandler.deleteFilesAndDirectory();

    assertFalse(Files.exists(Path.of(fileHandler.getSaveDirectory())));
    assertTrue(outputManager.getConsoleOutput().contains(FileHandlerConstants.DELETE_SUCCESS.getValue()));
  }

  @Test
  void testDeleteFilesAndDirectoryWithDirectoryNotFound() {
    fileHandler.deleteFilesAndDirectory();
    assertTrue(outputManager.getConsoleOutput().contains(FileHandlerConstants.DIRECTORY_FOR_DELETE_ABSENT.getValue()));
  }

  private String readContentFromFile(Path filePath) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
      StringBuilder content = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        content.append(line).append(System.lineSeparator());
      }
      return content.toString().trim();
    }
  }

  private static class OutputManagerStub extends OutputManager {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Override
    public void consolePrint(String message) {
      super.consolePrint(message);
      try {
        outputStream.write((message + System.lineSeparator()).getBytes());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    public String getConsoleOutput() {
      return outputStream.toString();
    }
  }
}
