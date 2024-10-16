package com.example.quickescapes.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightsQueries {
    public String sourceAirportCode;
    public String destinationAirportCode;
    public String date;
    public String returnDate;
    public String itineraryType;
    public String sortOrder;
    public int numAdults;
    public int numSeniors;
    public String classOfService;
    public int pageNumber;
    public String nonstop;
    public String currencyCode;
}
