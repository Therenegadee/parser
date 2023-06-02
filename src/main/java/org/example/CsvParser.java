package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {
    private final HTMLParser htmlParser = new HTMLParser();
    public CsvParser () {

    }
    public List<Info> parseCSV(File file) {
        List<Info> infoList = new ArrayList<>();
        try(FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                infoList.add(htmlParser.parsePage(line));
                System.out.printf("Read %s row%n", infoList.size());
            }
            bufferedReader.close();
            return infoList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
