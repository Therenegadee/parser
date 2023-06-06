package org.example;

import com.opencsv.CSVWriter;
import lombok.SneakyThrows;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CsvExporter {

    public CsvExporter() {
    }

    @SneakyThrows
    private void writeCSV(List<String> headerData, List<Info> infoList, String path) {
        File file = new File(path);
        FileWriter outputFile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputFile, ';');
        writer.writeNext(headerData.toArray(String[]::new));
        for (int i = 0; i < infoList.size(); i++) {
            Map<Integer, String> infoArray = (DataMap.getDataMap(infoList, i));
            writer.writeNext(infoArray.values().toArray(String[]::new));
            System.out.printf("Wrote %s row%n", i);
        }
    }

    private void saveCSV(List<Info> data, List<String> headerData, String pathOutput) {
        System.out.println("Введите путь к файлу вывода: ");
        writeCSV(headerData, data, pathOutput);
    }
    public void exportCSV(List<Info> data, List <String> headerData, String filePathOut) {
        CsvExporter csvExporter = new CsvExporter();
        Scanner sc = new Scanner(System.in);
        System.out.println(filePathOut);
        csvExporter.saveCSV(data, headerData, sc.next());
    }
}
