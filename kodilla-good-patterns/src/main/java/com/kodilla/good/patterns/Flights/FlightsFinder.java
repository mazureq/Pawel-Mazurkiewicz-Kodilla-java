package com.kodilla.good.patterns.Flights;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FlightsFinder {

    public List<String> findDestinations(String string) {

        return ListOfFlights.flightsList().stream()
                .filter(a -> a.getDeparture().equals(string))
                .map(a -> a.getDestination())
                .collect(Collectors.toList());
    }

    public List<String> findDepartures(String string) {
        return ListOfFlights.flightsList().stream()
                .filter(a -> a.getDestination().equals(string))
                .map(a -> a.getDeparture())
                .collect(Collectors.toList());
    }

    public Set<Flights> findNotDirect(String departure, String destination) {
        Set<Flights> undirectFly = new HashSet<>();
        for (int i = 0; i < findDestinations(departure).size(); i++) {
            String middleDestination = findDestinations(departure).get(i);
            for (int y = 0; y < findDepartures(destination).size(); y++) {
                String departureToFinalDestination = findDepartures(destination).get(y);
                if (departureToFinalDestination.equals(middleDestination)) {
                    undirectFly.add(new Flights(departure, middleDestination));
                    undirectFly.add(new Flights(middleDestination, destination));
                }
            }
        }
        return undirectFly;
    }
}
