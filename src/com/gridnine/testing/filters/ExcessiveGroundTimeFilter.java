package com.gridnine.testing.filters;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class ExcessiveGroundTimeFilter implements FlightFilter {
    @Override
    public boolean test(Flight flight) {
        List<Segment> segments = flight.getSegments();
        if (segments.size() < 2) return true;

        long totalGroundMinutes = 0;
        for (int i = 0; i < segments.size() - 1; i++) {
            LocalDateTime arrival = segments.get(i).getArrivalDate();
            LocalDateTime nextDeparture = segments.get(i + 1).getDepartureDate();
            totalGroundMinutes += Duration.between(arrival, nextDeparture).toMinutes();
        }
        return totalGroundMinutes <= 120;
    }
}