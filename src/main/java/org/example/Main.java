package org.example;


import java.io.*;


public class Main {

    static int amountOfRows = 1431;
    static String pathToExcelTable = "./animal_rescue_url.xlsx";
    static String pathToOutputFile = "./animal_rescue.xlsx";


    public static void main(String[] args) throws IOException {
        File file = new File(pathToExcelTable);
        var data = ExcelImportExport.readExcel(file, amountOfRows);
        ExcelImportExport.exportData(data, pathToOutputFile);
    }

}

