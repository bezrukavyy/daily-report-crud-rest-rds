package io.infomate.service.exemplar.daily.dao;

import java.util.List;

public interface DailyOperations {

    public DailyEntry getSingleEntry(String date, String location);
    public List<DailyEntry> getDailyEntriesByLocation(String location);
}
