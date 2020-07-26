package io.infomate.service.exemplar.daily.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DailyEntry {

    private String date;
    private String location;
    private Integer newCases;
    private Integer newDeaths;
    private Integer totalCases;
    private Integer totalDeaths;
    private double weeklyCases;
    private double weeklyDeaths;
    private double biweeklyCases;
    private double biweeklyDeaths;
}
