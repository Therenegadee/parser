package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvImport {
    public CsvImport () {}
    public List<Info> parseCSV(File file) throws IOException {
        List<Info> infoList = new ArrayList<>();
        HTMLParser htmlParser = new HTMLParser();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        String[] tempArr;
        int i = 0;
        while ((line = bufferedReader.readLine()) != null) {
            String delimiter = ",";
            tempArr = line.split(delimiter);
            for (String tempStr : tempArr) {
                infoList.add(htmlParser.parsePage(tempStr));
                System.out.printf("Read %s row%n", i);

            } i++;
        }
        bufferedReader.close();
        return infoList;
    }
}
