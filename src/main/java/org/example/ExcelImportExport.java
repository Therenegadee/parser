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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelImportExport {
    public static Map<Integer, String> HEADER_DATA = new LinkedHashMap<>(Map.of(
            0, "date_start",
            1, "date_end",
            2, "project_name",
            3, "funds_raised",
            4, "success_percentage",
            5, "people_support"
    ));
    public static List<Info> readExcel(File file, int amountOfRows) throws IOException {
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

    public static void exportData(List<Info> infoList, String pathToOutputFile) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet spreadsheet = wb.createSheet("Projects");
        fillTable(spreadsheet, 0, HEADER_DATA);
        for (int i = 1; i < infoList.size(); i++) {
            fillTable(spreadsheet, i, DataMap.getDataMap(infoList, i));
        }
        saveXlsx(wb, pathToOutputFile);
    }
    private static void fillTable(XSSFSheet spreadsheet, int rownum, Map<Integer, String> HeaderMap) {
        XSSFRow row;
        row = spreadsheet.createRow(rownum);
        Map<Integer, String> headerMap = HeaderMap;
        createCell(row, headerMap);
        System.out.println("Wrote %s row".formatted(rownum));
    }

    private static void createCell(XSSFRow row, Map<Integer, String> headerMap) {
        for (int j = 0; j < 7; j++) {
            Cell cell = row.createCell(j);
            cell.setCellValue(headerMap.get(j));
        }
    }

    private static void saveXlsx(XSSFWorkbook wb, String pathToOutputFile) {
        try (FileOutputStream out = new FileOutputStream((pathToOutputFile))) {
            wb.write(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
