package org.example;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataMap {
    public static Map<Integer, String> getDataMap(List<Info> infoList, int i) {
        Info info = infoList.get(i);
        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<Integer, String>();
        lhm.put (0, info.getDateStart());
        lhm.put (1, info.getDateEnd());
        lhm.put(2, info.getProjectName());
        lhm.put(3, info.getFundsRaised());
        lhm.put(4, info.getSuccessPercentage());
        lhm.put(5, info.getPeopleSupport());
        return lhm;
    }
}
