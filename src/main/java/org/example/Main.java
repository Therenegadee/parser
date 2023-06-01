package org.example;


import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        if (chooseFormat.equals(".csv")) {
            System.out.println(CHOOSE_FILE_PATH_IN);
            File inputFile = new File(SC.next());
            CsvImport csvImport = new CsvImport();
            CsvExport csvExport = new CsvExport();
            var data = csvImport.parseCSV(inputFile);
            System.out.println(CHOOSE_FILE_PATH_OUT);
            csvExport.saveCSV(data, HEADER_DATA, SC.next());
        } else if (chooseFormat.equals(".xlsx")) {
            System.out.println(CHOOSE_FILE_PATH_IN);
            File file = new File(SC.next());
            ExcelImport excelImport = new ExcelImport();
            ExcelExport excelExport = new ExcelExport(HEADER_DATA);
            var data = excelImport.readExcel(file, AMOUNT_OF_ROWS);
            System.out.println(CHOOSE_FILE_PATH_OUT);
            excelExport.exportData(data, SC.next());
//        } else if (chooseFormat.equals(".doc")) {
//            System.out.println(CHOOSE_FILE_PATH_IN);
//            File file = new File(SC.next());
//            System.out.println(CHOOSE_FILE_PATH_OUT);
        } else {
            System.err.println("Ошибка");
        }
    }
}

