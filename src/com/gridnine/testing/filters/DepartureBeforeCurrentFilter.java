package com.gridnine.testing.filters;

import com.gridnine.testing.Flight;
import java.time.LocalDateTime;

public class DepartureBeforeCurrentFilter implements FlightFilter {
    @Override
    public boolean test(Flight flight) {
        return flight.getSegments().stream()
                .noneMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()));
    }
}