package org.example;


import java.io.*;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static final int AMOUNT_OF_ROWS = 4;
    private static final String CHOOSE_FILE_FORMAT = "Введите формат файла, из которого вы хотите прочитать ссылки: .csv или .xlsx";
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

    public static void main(String[] args) throws IOException {
        System.out.println(CHOOSE_FILE_FORMAT);
        String chooseFormat = SC.next();
        switch (chooseFormat) {
            case ".csv" -> csvParse();
            case ".xlsx" -> excelParse();
        }
    }

    public static void csvParse () throws IOException {
        System.out.println(CHOOSE_FILE_PATH_IN);
        File inputFile = new File(SC.next());
        CsvParser csvParser = new CsvParser();
        CsvExporter csvExporter = new CsvExporter();
        var data = csvParser.parseCSV(inputFile);
        System.out.println(CHOOSE_FILE_PATH_OUT);
        csvExporter.saveCSV(data, HEADER_DATA, SC.next());
    }
    public static void excelParse () throws IOException {
        System.out.println(CHOOSE_FILE_PATH_IN);
        File file = new File(SC.next());
        ExcelParser excelParser = new ExcelParser();
        ExcelExporter excelExporter = new ExcelExporter(HEADER_DATA);
        var data = excelParser.parseExcel(file, AMOUNT_OF_ROWS);
        System.out.println(CHOOSE_FILE_PATH_OUT);
        excelExporter.exportData(data, SC.next());
    }
}

