package dec.starter.constant;

/**
 * Enumeration of constants related to file handling.
 */
public enum FileHandlerConstants {
  OS_NAME("os.name"),
  USER_HOME("user.home"),
  CURRENT_WORKING_DIR("user.dir"),
  WIN_PATH_TO_TESTS("\\generated_tests\\"),
  UNIX_PATH_TO_TESTS("/generated_tests/"),
  SAVE_SUCCESS("Данные успешно записаны в файл."),
  SAVE_UNSUCCESSFUL("Сохранение в файл не было произведено по причине: "),
  DIRECTORY_ABSENT("Папка для сохранения файлов отсутствует, возможно, нет прав для ее создания."),
  DIRECTORY_FOR_DELETE_ABSENT("Папка с файлами тестов отсутствует, нечего удалять."),
  PATH_TO_SAVED_FILE("Сохраненный файл находится по адресу: "),
  DOCS("Документы"),
  FILE_TEST_PREFIX("test_"),
  FILE_C_EXT(".c"),
  WIN("win"),
  NUX("nux"),
  MAC("mac"),
  NIX("nix"),
  DELETE_SUCCESS("Удаление папки прошло успешно."),
  DELETE_UNSUCCESSFUL("Удаление папки не удалось. Возможно, нет прав для удаления."),
  DELETE_FILE_UNSUCCESSFUL("Удаление файла не удалось. Возможно, нет прав для удаления.");
  private final String value;

  FileHandlerConstants(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
