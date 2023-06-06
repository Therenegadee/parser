package org.example;



import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvParser {
    private final HTMLParser htmlParser = new HTMLParser();
    public CsvParser () {}
    public List<Info> readCSV(File file) {
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

    public List<Info> parseCSV(String chooseFilePathIn) {
        System.out.println(chooseFilePathIn);
        Scanner sc = new Scanner(System.in);
        File inputFile = new File(sc.next());
        CsvParser csvParser = new CsvParser();
        var data = csvParser.readCSV(inputFile);
        return data;
    }
}