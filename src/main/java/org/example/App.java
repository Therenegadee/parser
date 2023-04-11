package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.*;


public class App {

    public static void main(String[] args) throws IOException {
        File file = new File("./charity_url.xlsx");
        var data = readExcel(file);
        exportData(data);
    }

    public static Info getInfo(String cell) throws IOException {
        Document doc = Jsoup.connect(cell).get();
        String start_time = doc.getElementsByAttribute("start_time").attr("start_time");
        String end_time = doc.getElementsByAttribute("end_time").attr("end_time");
        String fundsRaised = doc.getElementsByAttribute("backer_money").get(0).childNodes().get(0).toString();
        String projectName = doc.getElementsByTag("title").get(0).childNodes().get(0).toString();
        String successPercentage = doc.getElementsByAttribute("rate").get(0).childNodes().get(0).toString();
        String peopleSupport = doc.getElementsByAttribute("backer_count").get(0).childNodes().get(0).toString();
        return new Info(start_time, end_time, projectName, fundsRaised, successPercentage, peopleSupport);
    }

    public static List<Info> readExcel(File file) throws IOException {
        List<Info> infoList = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workBook.getSheetAt(0);
        for (int i = 0; i < 20; i++) {
            XSSFRow row = sheet.getRow(i);
            String cell = row.getCell(0).toString();
            infoList.add(getInfo(cell));
            System.out.println("Read %s row".formatted(i));
        }
        inputStream.close();
        return infoList;
    }

    public static void exportData(List<Info> infoList) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet spreadsheet = wb.createSheet("Projects");
        XSSFRow row;

        row = spreadsheet.createRow(0);
        Map<Integer, String> headerMap = getHeaderMap();
        for (int j = 0; j < 7; j++) {
            Cell cell = row.createCell(j);
            cell.setCellValue(headerMap.get(j));
        }
        System.out.println("Wrote %s row".formatted(0));

        for (int i = 1; i < infoList.size(); i++) {
            row = spreadsheet.createRow(i);
            Map<Integer, String> dataMap = getDataMap(infoList, i);
            for (int j = 0; j < 7; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(dataMap.get(j));
            }
            System.out.println("Wrote %s row".formatted(i));
        }
        saveXlsx(wb);
    }

    private static void saveXlsx(XSSFWorkbook wb) {
        try (FileOutputStream out = new FileOutputStream(("./charity.xlsx"))) {
            wb.write(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<Integer, String> getDataMap(List<Info> infoList, int i) {
        Info info = infoList.get(i);
        return new LinkedHashMap<>(Map.of(
                0, info.getDateStart(),
                1, info.getDateEnd(),
                2, info.getProjectName(),
                3, info.getFundsRaised(),
                4, info.getSuccessPercentage(),
                5, info.getPeopleSupport()
        ));
    }
    private static Map<Integer, String> getHeaderMap() {
        return new LinkedHashMap<>(Map.of(
                0, "date_start",
                1, "date_end",
                2, "project_name",
                3, "funds_raised",
                4, "success_percentage",
                5, "people_support"
        ));
    }
}

