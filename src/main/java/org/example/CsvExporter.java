package org.example;

import com.opencsv.CSVWriter;

import java.io.*;
import java.util.List;
import java.util.Map;

public class CsvExporter {

    public CsvExporter() {
    }

    private void writeCSV(List<String> headerData, List<Info> infoList, String path) throws IOException {
        File file = new File(path);
        FileWriter outputFile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputFile);
        writer.writeNext(headerData.toArray(String[]::new));
        for (int i = 0; i < infoList.size(); i++) {
            Map<Integer, String> infoArray = (DataMap.getDataMap(infoList, i));
            writer.writeNext(infoArray.values().toArray(new String[0]));
            System.out.printf("Wrote %s row%n", i);
            i++;
        }
    }

    public void saveCSV(List<Info> data, List<String> headerData, String pathOutput) throws IOException {
        System.out.println("Введите путь к файлу вывода: ");
        writeCSV(headerData, data, pathOutput);
    }
}
