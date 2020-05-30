package com.example.countriespenkov;

public class API {

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }


    // https://restcountries.eu/rest/v2/all
    private String name;
    private int population;
    private String alpha2Code;
}