package org.example;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ExcelImportExport {

    private final Map<Integer, String> headerData;

    public ExcelImportExport(Map<Integer, String> headerData) {
        this.headerData = headerData;
    }

    public List<Info> readExcel(File file, int amountOfRows) throws IOException {
        List<Info> infoList = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workBook.getSheetAt(0);
        HTMLParser htmlParser = new HTMLParser();
        for (int i = 0; i < amountOfRows; i++) {
            XSSFRow row = sheet.getRow(i);
            String cell = row.getCell(0).toString();
            infoList.add(htmlParser.parsePage(cell));
            System.out.println("Read %s row".formatted(i));
        }
        inputStream.close();
        return infoList;
    }

    private static void createCell(XSSFRow row, Map<Integer, String> data) {
        for (int j = 0; j < 7; j++) {
            Cell cell = row.createCell(j);
            cell.setCellValue(data.get(j));
        }
    }

    private void fillHeader(XSSFSheet spreadsheet) {
        XSSFRow row;
        row = spreadsheet.createRow(0);
        createCell(row, headerData);
        System.out.println("Wrote header");
    }

    private void fillTable(XSSFSheet spreadsheet, int rownum, Map<Integer, String> data) {
        XSSFRow row;
        row = spreadsheet.createRow(rownum);
        createCell(row, data);
        System.out.println("Wrote %s row".formatted(rownum));
    }

    public void exportData(List<Info> infoList, String pathToOutputFile) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet spreadsheet = wb.createSheet("Projects");
        fillHeader(spreadsheet);
        for (int i = 1; i < infoList.size(); i++) {
            fillTable(spreadsheet, i, DataMap.getDataMap(infoList, i));
        }
        saveXlsx(wb, pathToOutputFile);
    }

    private void saveXlsx(XSSFWorkbook wb, String pathToOutputFile) {
        try (FileOutputStream out = new FileOutputStream((pathToOutputFile))) {
            wb.write(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
