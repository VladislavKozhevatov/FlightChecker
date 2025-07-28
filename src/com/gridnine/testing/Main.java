package com.gridnine.testing;

import com.gridnine.testing.filters.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.filters.DepartureBeforeCurrentFilter;
import com.gridnine.testing.filters.ExcessiveGroundTimeFilter;
import com.gridnine.testing.filters.FlightFilter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Получаем тестовые данные через FlightBuilder
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Все перелёты:");
        flights.forEach(System.out::println);

        System.out.println("\nФильтр 1: Исключены перелёты с вылетом до текущего времени");
        filterAndPrint(flights, new DepartureBeforeCurrentFilter());

        System.out.println("\nФильтр 2: Исключены перелёты с сегментами (прилёт < вылета)");
        filterAndPrint(flights, new ArrivalBeforeDepartureFilter());

        System.out.println("\nФильтр 3: Исключены перелёты с временем на земле > 2 часов");
        filterAndPrint(flights, new ExcessiveGroundTimeFilter());
    }

    private static void filterAndPrint(List<Flight> flights, FlightFilter filter) {
        flights.stream()
                .filter(filter::test)
                .forEach(System.out::println);
    }
}