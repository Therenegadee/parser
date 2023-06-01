package org.example;


import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {

    private static final int amountOfRows = 4;
    private static final Scanner sc = new Scanner(System.in);
    private static final Map<Integer, String> HEADER_DATA = new LinkedHashMap<>(Map.of(
            0, "date_start",
            1, "date_end",
            2, "project_name",
            3, "funds_raised",
            4, "success_percentage",
            5, "people_support"
    ));

    public static void main(String[] args) throws IOException {
        System.out.println("Введите формат файла, из которого вы хотите прочитать ссылки: .csv или .xlsx");
        String chooseFormat = sc.next();
        if (chooseFormat.equals(".csv")) {
            System.out.println("Введите путь к файлу, из которого хотите читать ссылки: ");
            File inputFile = new File(sc.next());
            CsvImport csvImport = new CsvImport();
            CsvExport csvExport= new CsvExport();
            var data = csvImport.parseCSV(inputFile);
            System.out.println("Введите путь к файлу вывода: ");
            csvExport.saveCSV(data, HEADER_DATA, sc.next());
        } else if (chooseFormat.equals(".xlsx")) {
            System.out.println("Введите путь к файлу, из которого хотите читать ссылки: ");
            File file = new File(sc.next());
            ExcelImport excelImport = new ExcelImport();
            ExcelExport excelExport = new ExcelExport(HEADER_DATA);
            var data = excelImport.readExcel(file, amountOfRows);
            System.out.println("Введите путь к файлу вывода: ");
            excelExport.exportData(data, sc.next());
        } else {
            System.err.println("ошибка");
        }
    }

}

