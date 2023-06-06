package org.example;

import java.util.List;
import java.util.Scanner;

public class ParserApplication {
    private static final String CHOOSE_INPUT_FILE_FORMAT = "Введите формат файла, из которого вы хотите прочитать ссылки: .csv или .xlsx";
    private static final String CHOOSE_OUTPUT_FILE_FORMAT = "Введите формат файла вывода: .csv или .xlsx";
    private static final String CHOOSE_FILE_PATH_IN = "Введите путь к файлу, из которого хотите читать ссылки: ";
    private static final String CHOOSE_FILE_PATH_OUT = "Введите путь к файлу вывода: ";
    private static final Scanner SC = new Scanner(System.in);
    private static final List<String> HEADER_DATA = List.of(
            "date_start",
            "date_end",
            "project_name",
            "funds_raised",
            "success_percentage",
            "people_support"
    );

    public static void main(String[] args) {
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
        System.out.println(CHOOSE_OUTPUT_FILE_FORMAT);
        String chooseFormatOutput = SC.next();
        CsvExporter csvExporter = new CsvExporter();
        ExcelExporter excelExporter = new ExcelExporter(HEADER_DATA);
        switch (chooseFormatOutput) {
            case ".csv" -> csvExporter.exportCSV(parsedData, HEADER_DATA, CHOOSE_FILE_PATH_OUT);
            case ".xlsx" -> excelExporter.excelExport(parsedData, HEADER_DATA, CHOOSE_FILE_PATH_OUT);
            default -> throw new IllegalStateException("Unexpected value: " + chooseFormatOutput);
        }
    }
}
