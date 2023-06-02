package org.example;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelParser {
    public ExcelParser () {
    }
    public List<Info> parseExcel(File file, int amountOfRows) throws IOException {
        List<Info> infoList = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workBook.getSheetAt(0);
        HTMLParser htmlParser = new HTMLParser();
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            String cell = row.getCell(0).toString();
            infoList.add(htmlParser.parsePage(cell));
            System.out.println("Read %s row".formatted(i));
        }
        inputStream.close();
        return infoList;
    }

}
