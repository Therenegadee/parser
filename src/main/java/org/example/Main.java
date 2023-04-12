package org.example;


import java.io.*;
import java.util.*;


public class Main {

    static int amountOfRows = 1200;
    static String pathToExcelTable = "./board_games_url.xlsx";
    static String pathToOutputFile = "./board_games.xlsx";

    public static Map<Integer, String> HEADER_DATA = new LinkedHashMap<>(Map.of(
            0, "date_start",
            1, "date_end",
            2, "project_name",
            3, "funds_raised",
            4, "success_percentage",
            5, "people_support"
    ));
    public static void main(String[] args) throws IOException {
        File file = new File(pathToExcelTable);
        var data = ExcelImportExport.readExcel(file, amountOfRows);
        ExcelImportExport.exportData(data, HEADER_DATA, pathToOutputFile);
    }

}

