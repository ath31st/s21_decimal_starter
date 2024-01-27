package dec.starter.constant;

public enum FileHandlerConstants {
  OS_NAME("os.name"),
  USER_HOME("user.home"),
  USER_DIR("user.dir"),
  WIN_PATH("\\Documents\\s21_decimal_starter\\"),
  UNIX_PATH("/Documents/s21_decimal_starter/"),
  SAVE_SUCCESS("Данные успешно записаны в файл."),
  SAVE_UNSUCCESSFUL("Сохранение в файл не было произведено по причине: "),
  DIRECTORY_ABSENT("Папка для сохранения файлов отсутствует, возможно, нет прав для ее создания."),
  WIN("win"),
  NUX("nux"),
  MAC("mac"),
  NIX("nix"),
  DELETE_SUCCESS("Удаление папки прошло успешно."),
  DELETE_UNSUCCESSFUL("Удаление папки не удалось, возможно, нет прав для удаления.");
  private final String value;

  FileHandlerConstants(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
