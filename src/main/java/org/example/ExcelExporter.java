package org.example;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class ExcelExporter {

    private final List<String> headerData;

    public ExcelExporter(List<String> headerData) {
        this.headerData = headerData;
    }

    private void createHeader(XSSFRow row) {
        for (int j = 0; j < 6; j++) {
            Cell cell = row.createCell(j);
            cell.setCellValue(headerData.get(j));
        }
    }

    private void createCell(XSSFRow row, Map<Integer, String> data) {
        for (int j = 0; j < 6; j++) {
            Cell cell = row.createCell(j);
            cell.setCellValue(data.get(j));
        }
    }

    private void fillHeader(XSSFSheet spreadsheet) {
        XSSFRow row;
        row = spreadsheet.createRow(0);
        createHeader(row);
        System.out.println("Wrote header");
    }

    private void fillTable(XSSFSheet spreadsheet, int rownum, Map<Integer, String> data) {
        XSSFRow row;
        row = spreadsheet.createRow(rownum);
        createCell(row, data);
        System.out.printf("Wrote %s row%n", rownum);
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
        for (int i = 0; i < infoList.size(); i++) {
            int rownum = 1+i;
            fillTable(spreadsheet, rownum, DataMap.getDataMap(infoList, i));
        }
        saveXlsx(wb, pathToOutputFile);
    }
}
