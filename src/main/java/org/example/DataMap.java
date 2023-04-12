package org.example;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataMap {
    public static Map<Integer, String> getDataMap(List<Info> infoList, int i) {
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
}
