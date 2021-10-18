package com.mushipeas.coronavirustracker;

import com.mushipeas.coronavirustracker.daos.CoronavirusDAO;
import com.mushipeas.coronavirustracker.services.CoronavirusDataService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CoronavirusDataServiceTest {
    
    @Mock private CoronavirusDAO coronavirusDAO;
    private CoronavirusDataService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CoronavirusDataService(coronavirusDAO);
    }

    @Test
    void fetchesAllCovidData() {
        // when
        underTest.getAllLocationStats();
        // then
        verify(coronavirusDAO).selectAllLocationStats();
    }


    @Test
    void fetchesTotalReportedCases() {
        // when
        underTest.getTotalReportedCases();
        // then
        verify(coronavirusDAO).calculateTotalReportedCases();
    }


    @Test
    void fetchesNewCasesToday() {
        // when
        underTest.getNewCasesToday();
        // then
        verify(coronavirusDAO).calculateNewCasesToday();
    }

}