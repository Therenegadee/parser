package org.example;


import java.io.*;
import java.util.List;
import java.util.Scanner;


public class Main {

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

    public static void main(String[] args) throws IOException {
        System.out.println(CHOOSE_INPUT_FILE_FORMAT);
        String chooseFormatInput = SC.next();
        List<Info> parsedData = switch (chooseFormatInput) {
            case ".csv" -> csvParse();
            case ".xlsx" -> excelParse();
            /*case ".docx" -> {
            }*/
            default -> throw new IllegalStateException("Unexpected value: " + chooseFormatInput);
        };
        System.out.println(CHOOSE_OUTPUT_FILE_FORMAT);
        String chooseFormatOutput = SC.next();
        switch (chooseFormatOutput) {
            case ".csv" -> csvExport(parsedData);
            case ".xlsx" -> excelExport(parsedData);
            default -> throw new IllegalStateException("Unexpected value: " + chooseFormatOutput);
        }
    }

    public static List<Info> csvParse() {
        System.out.println(CHOOSE_FILE_PATH_IN);
        File inputFile = new File(SC.next());
        CsvParser csvParser = new CsvParser();
        var data = csvParser.parseCSV(inputFile);
        return data;
    }

    public static void csvExport(List<Info> data) {
        CsvExporter csvExporter = new CsvExporter();
        System.out.println(CHOOSE_FILE_PATH_OUT);
        csvExporter.saveCSV(data, HEADER_DATA, SC.next());
    }


    public static List<Info> excelParse() throws IOException {
        System.out.println(CHOOSE_FILE_PATH_IN);
        File file = new File(SC.next());
        ExcelParser excelParser = new ExcelParser();
        var data = excelParser.parseExcel(file);
        return data;
    }

    public static void excelExport (List<Info> data) {
        ExcelExporter excelExporter = new ExcelExporter(HEADER_DATA);
        System.out.println(CHOOSE_FILE_PATH_OUT);
        excelExporter.exportData(data, SC.next());
    }

    /*public static void docxParse() {

    }*/
}

