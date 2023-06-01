package org.example;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class ExcelExport {

    private final Map<Integer, String> headerData;

    public ExcelExport(Map<Integer, String> headerData) {
        this.headerData = headerData;
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
    private void saveXlsx(XSSFWorkbook wb, String pathToOutputFile) {
        try (FileOutputStream out = new FileOutputStream((pathToOutputFile))) {
            wb.write(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
}
