package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.*;


public class App {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/aye/Desktop/megaDataSet/charity_url.xlsx");
        readExcel(file).forEach(info -> {
            try {
                exportData(info.getDateStart(), info.getDateEnd(), info.getProjectName(), info.getFundsRaised(), info.getSuccessPercentage(), info.getPeopleSupport());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
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
        for (int i = 1; i < 20; i++) {
            XSSFRow row = sheet.getRow(i);
            String cell = row.getCell(0).toString();
            infoList.add(getInfo(cell));
        }
        inputStream.close();
        return infoList;
    }

    public static void exportData(String start_time, String end_time, String projectName, String fundsRaised, String successPercentage, String peopleSupport) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet spreadsheet = wb.createSheet("Projects");
        XSSFRow row;
        Map<String, Object[]> projects = new TreeMap<>();
        int s = 1;
        projects.put(String.valueOf(s), new Object[]{"start_time", "end_time", "projectName", "fundsRaised", "successPercentage", "peopleSupport"});
        for (int i = 0; i < 20; i++) {
            s++;
            String p = String.valueOf(s);
            projects.put(p, new Object[]{start_time, end_time, projectName, fundsRaised, successPercentage, peopleSupport});
            }
            Set<String> keyid = projects.keySet();
            int rowid = 0;
            for (String key : keyid) {
                row = spreadsheet.createRow(rowid++);
                Object[] objectArr = projects.get(key);
                int cellid = 0;
                for (Object obj : objectArr) {
                    Cell cell = row.createCell(cellid++);
                    cell.setCellValue((String) obj);
            }
        }
        FileOutputStream out = new FileOutputStream(("/Users/aye/Desktop/megaDataSet/charity.xlsx"));
        wb.write(out);
        out.close();
    }
}

