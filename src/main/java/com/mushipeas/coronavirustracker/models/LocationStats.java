package com.mushipeas.coronavirustracker.models;

public class LocationStats {
    private String state;
    private String country;
    private int casesToDate;
    private int caseDifferenceToday;


    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCasesToDate() {
        return this.casesToDate;
    }

    public void setCasesToDate(int casesToDate) {
        this.casesToDate = casesToDate;
    }

    public int getCaseDifferenceToday() {
        return this.caseDifferenceToday;
    }

    public void setCaseDifferenceToday(int caseDifferenceToday) {
        this.caseDifferenceToday = caseDifferenceToday;
    }
    
    @Override
    public String toString() {
        return "{" +
            " state='" + getState() + "'" +
            ", country='" + getCountry() + "'" +
            ", casesToDate='" + getCasesToDate() + "'" +
            ", caseDifferenceToday='" + getCaseDifferenceToday() + "'" +
            "}";
    }
}

