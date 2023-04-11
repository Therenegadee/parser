package org.example;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class HTMLParser {
    @SneakyThrows
    public Info parsePage (String cell) {
        Document doc = Jsoup.connect(cell).get();
        String start_time = doc.getElementsByAttribute("start_time").attr("start_time");
        String end_time = doc.getElementsByAttribute("end_time").attr("end_time");
        String fundsRaised = doc.getElementsByAttribute("backer_money").get(0).childNodes().get(0).toString();
        String projectName = doc.getElementsByTag("title").get(0).childNodes().get(0).toString();
        String successPercentage = doc.getElementsByAttribute("rate").get(0).childNodes().get(0).toString();
        String peopleSupport = doc.getElementsByAttribute("backer_count").get(0).childNodes().get(0).toString();
        return new Info(start_time, end_time, projectName, fundsRaised, successPercentage, peopleSupport);
    }
}
