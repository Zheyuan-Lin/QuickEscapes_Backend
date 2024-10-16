package com.example.quickescapes.service;

import com.example.quickescapes.dao.FlightsQueries;

public interface FlightsService {
    public String searchFlightsOneWay(FlightsQueries flights);
    public String searchFlightsRoundTrip(FlightsQueries flights);

    public String searchAirport(String airportCode);
}
