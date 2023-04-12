package org.example;


import java.io.*;


public class Main {

    static int amountOfRows = 4;
    static String pathToExcelTable = "./charity_url.xlsx";
    static String pathToOutputFile = "./charity.xlsx";


    public static void main(String[] args) throws IOException {
        File file = new File(pathToExcelTable);
        var data = ExcelImportExport.readExcel(file,amountOfRows);
        ExcelImportExport.exportData(data, pathToOutputFile);
    }

}

