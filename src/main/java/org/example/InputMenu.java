package org.example;

import java.util.List;
import java.util.Scanner;

public class InputMenu {
    private static final String CHOOSE_INPUT_FILE_FORMAT = "Введите формат файла, из которого вы хотите прочитать ссылки: .csv или .xlsx";
    private static final String CHOOSE_FILE_PATH_IN = "Введите путь к файлу, из которого хотите читать ссылки: ";
    private static final Scanner SC = new Scanner(System.in);

    public InputMenu () {}

    public List<Info> runInputMenu () {
        System.out.println(CHOOSE_INPUT_FILE_FORMAT);
        String chooseFormatInput = SC.next();
        CsvParser csvParser = new CsvParser();
        ExcelParser excelParser = new ExcelParser();
        List<Info> parsedData = switch (chooseFormatInput) {
            case ".csv" -> csvParser.parseCSV(CHOOSE_FILE_PATH_IN);
            case ".xlsx" -> excelParser.parseExcel(CHOOSE_FILE_PATH_IN);
            /*case ".docx" -> {
            }*/
            default -> throw new IllegalStateException("Unexpected value: " + chooseFormatInput);
        };
        return parsedData;
    }
}
