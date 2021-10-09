package com.mushipeas.coronavirustracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.mushipeas.coronavirustracker.services.models.LocationStats;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CoronavirusDataService {
    private static String COVID_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

private List<LocationStats> allLocationStats = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchCovidData(){
        List<LocationStats> updatedLocationStats = new ArrayList<>();
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(COVID_DATA_URL))
            .build();

        HttpResponse<String> httpResponse;
        try {
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

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<LocationStats> getAllLocationStats() {
        return this.allLocationStats;
    }

    public int getTotalReportedCases() {
        int totalCases = 0;
        for (LocationStats stat : allLocationStats) {
            totalCases += stat.getCasesToDate();
        }
        return totalCases;
    }

    public int getNewCasesToday() {
        int totalCases = 0;
        for (LocationStats stat : allLocationStats) {
            totalCases += stat.getCaseDifferenceToday();
        }
        return totalCases;
    }

}
