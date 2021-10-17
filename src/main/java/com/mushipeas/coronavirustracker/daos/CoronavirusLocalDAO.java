package com.mushipeas.coronavirustracker.daos;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.mushipeas.coronavirustracker.models.LocationStats;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

@Repository("localDAO")
public class CoronavirusLocalDAO implements CoronavirusDAO {

    private static String COVID_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<LocationStats> allLocationStats = new ArrayList<>();

    
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchCovidData() throws IOException, InterruptedException {
        List<LocationStats> updatedLocationStats = new ArrayList<>();
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(COVID_DATA_URL))
            .build();

        HttpResponse<String> httpResponse;

        httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader reader = new StringReader(httpResponse.body());

        CSVFormat parser = CSVFormat.Builder.create()
                                            .setHeader()
                                            .build();

        Iterable<CSVRecord> records = parser.parse(reader);
        for (CSVRecord record : records) {
            LocationStats locationStats = new LocationStats();
            
            locationStats.setState(record.get("Province/State"));
            locationStats.setCountry(record.get("Country/Region"));
            int casesToDate = Integer.parseInt(record.get(record.size()-1));
            int casesYesterday = Integer.parseInt(record.get(record.size()-2));
            int caseDifferenceToday = casesToDate - casesYesterday;
            locationStats.setCasesToDate(casesToDate);
            locationStats.setCaseDifferenceToday(caseDifferenceToday);

            updatedLocationStats.add(locationStats);
            }

        allLocationStats = updatedLocationStats;
    }    

    @Override
    public List<LocationStats> selectAllLocationStats() {
        return this.allLocationStats;
    }

    @Override
    public int calculateTotalReportedCases() {
        int totalCases = 0;
        for (LocationStats stat : allLocationStats) {
            totalCases += stat.getCasesToDate();
        }
        return totalCases;
    }

    @Override
    public int calculateNewCasesToday() {
        int totalCases = 0;
        for (LocationStats stat : allLocationStats) {
            totalCases += stat.getCaseDifferenceToday();
        }
        return totalCases;
    }
    
}
