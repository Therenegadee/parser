package org.example;

import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExcelParser {
    public ExcelParser () {
    }
    @SneakyThrows
    private List<Info> readExcel(File file) {
        List<Info> infoList = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workBook.getSheetAt(0);
        HTMLParser htmlParser = new HTMLParser();
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            String cell = row.getCell(0).toString();
            infoList.add(htmlParser.parsePage(cell));
            System.out.printf("Read %s row%n", i);
        }
        inputStream.close();
        return infoList;
    }

    @SneakyThrows
    public List<Info> parseExcel (String chooseFilePathIn) {
        System.out.println(chooseFilePathIn);
        Scanner sc = new Scanner(System.in);
        File file = new File(sc.next());
        ExcelParser excelParser = new ExcelParser();
        var data = excelParser.readExcel(file);
        return data;
    }
}
