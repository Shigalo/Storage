package by.bsuir.shigalo7.Services;

import by.bsuir.shigalo7.Entities.Request;
import by.bsuir.shigalo7.Entities.Tour;
import by.bsuir.shigalo7.Entities.User;
import by.bsuir.shigalo7.Repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    FlightService flightService;
    @Autowired
    TourService tourService;
    @Autowired
    UserService userService;

    public void addRequest(Request request) {
        requestRepository.save(request);
    }

    public List<Request> getRequestList(User user) {
        return requestRepository.findByUser(user);
    }

    public Request getById(String id) {
        return requestRepository.findById(Integer.valueOf(id));
    }

    public void RequestRemoveById(int id) {
        requestRepository.deleteById(id);
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public void addRequest(Integer places, String questions, String flightId, Double cost, String id) {
        Request request = new Request();
        request.setCost(cost);
        if(flightId == null) request.setFlight(null);
        else request.setFlight(flightService.getById(flightId));
        Tour tour = tourService.findById(id);
        request.setTour(tour);
        request.setQuestions(questions);
        request.setPlaces(places);
        request.setUser(userService.getCurrentUser());
        request.setConfirm(false);
//        tour.setPlaces(tour.getPlaces() - places);
        requestRepository.save(request);
    }

    public void confirm(String id) {
        Request request = requestRepository.findById(Integer.valueOf(id));
        request.setConfirm(true);
        Tour tour = request.getTour();
        tour.setPlaces(tour.getPlaces() - request.getPlaces());
        tourService.update(tour);
        requestRepository.save(request);
    }
}
