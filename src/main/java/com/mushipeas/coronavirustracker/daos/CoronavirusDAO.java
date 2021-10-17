package com.mushipeas.coronavirustracker.daos;

import java.util.List;

import com.mushipeas.coronavirustracker.models.LocationStats;

public interface CoronavirusDAO {

    List<LocationStats> selectAllLocationStats();

    int calculateTotalReportedCases();

    int calculateNewCasesToday();
    
}
