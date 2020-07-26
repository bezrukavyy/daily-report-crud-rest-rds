package io.infomate.service.exemplar.daily.dao;

import io.infomate.service.exemplar.daily.MySqlConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DailyOperationsImpl implements DailyOperations {

    private final static String SELECT_BY_DATE_LOCATION =
            "select * from daily where date = date(?) and upper(location) = upper(?)";
    private final static String SELECT_BY_LOCATION = "select * from daily where upper(location) = upper(?)";

    private static final Logger log = LoggerFactory.getLogger(DailyOperationsImpl.class);

    private MySqlConnectionManager connectionManager;

    public DailyOperationsImpl(MySqlConnectionManager connectionManager) {

        this.connectionManager = connectionManager;
    }

    @Override
    public DailyEntry getSingleEntry(String date, String location) {

        try {
            Connection connection = this.connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_DATE_LOCATION);
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, location);
            ResultSet resultSet = preparedStatement.executeQuery();
            DailyEntry dailyEntry = null;
            if (resultSet.next()) {
                dailyEntry = this.readCurrentDailyEntry(resultSet);
            }

            return dailyEntry;
        }

        catch (SQLException sqle) {
            log.error("SQL error: {}", sqle.getMessage(), sqle);
        }

        return null;
    }

    @Override
    public List<DailyEntry> getDailyEntriesByLocation(String location) {

        try {
            Connection connection = this.connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_LOCATION);
            preparedStatement.setString(1, location);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<DailyEntry> dailyEntryList = new ArrayList<>();
            while(resultSet.next()) {
                DailyEntry dailyEntry = readCurrentDailyEntry(resultSet);
                dailyEntryList.add(dailyEntry);
            }

            return dailyEntryList;
        }

        catch (SQLException sqle) {
            log.error("SQL error: {}", sqle.getMessage(), sqle);
        }

        return null;
    }

    private DailyEntry readCurrentDailyEntry(ResultSet resultSet)
            throws SQLException
    {
        DailyEntry dailyEntry = DailyEntry.builder()
                .date(resultSet.getString("date"))
                .location(resultSet.getString("location"))
                .newCases(resultSet.getInt("new_cases"))
                .newDeaths(resultSet.getInt("new_deaths"))
                .totalCases(resultSet.getInt("total_cases"))
                .totalDeaths(resultSet.getInt("total_deaths"))
                .weeklyCases(resultSet.getDouble("weekly_cases"))
                .weeklyDeaths(resultSet.getDouble("weekly_deaths"))
                .biweeklyCases(resultSet.getDouble("biweekly_cases"))
                .biweeklyDeaths(resultSet.getDouble("biweekly_deaths"))
                .build()
                ;

        return dailyEntry;
    }
}
