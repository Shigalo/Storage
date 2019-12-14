package by.bsuir.shigalo7.Services;

import by.bsuir.shigalo7.Entities.Flight;
import by.bsuir.shigalo7.Entities.Tour;
import by.bsuir.shigalo7.Repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    public void addFlight(Flight flight) {
        System.out.println(flight);
        flightRepository.save(flight);
    }

    public Flight getById(String id) {
        return flightRepository.findById(Integer.valueOf(id));
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public void removeById(int id) {
        flightRepository.deleteById(id);
    }

    public List<Flight> findByTour(Tour tour) {
        return flightRepository.findByTour(tour);
    }

    public void updateLength(Tour tour) {
        for(Flight flight : findByTour(tour)) {
            flight.setArrival(tour.getDate().plusDays(tour.getLength()));
        }
    }
}
