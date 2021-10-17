package com.mushipeas.coronavirustracker.services;

import java.util.List;

import com.mushipeas.coronavirustracker.daos.CoronavirusDAO;
import com.mushipeas.coronavirustracker.models.LocationStats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CoronavirusDataService {

    private final CoronavirusDAO coronavirusDAO;

    @Autowired
    public CoronavirusDataService(@Qualifier("localDAO") CoronavirusDAO coronavirusDAO) {
        this.coronavirusDAO = coronavirusDAO;
    }

    public List<LocationStats> getAllLocationStats() {
        return this.coronavirusDAO.selectAllLocationStats();
    }

    public int getTotalReportedCases() {
        return this.coronavirusDAO.calculateTotalReportedCases();
    }

    public int getNewCasesToday() {
        return this.coronavirusDAO.calculateNewCasesToday();
    }

}
