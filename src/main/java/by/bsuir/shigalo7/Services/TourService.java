package by.bsuir.shigalo7.Services;

import by.bsuir.shigalo7.Entities.Tour;
import by.bsuir.shigalo7.Repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TourService {

    @Autowired
    TourRepository tourRepository;

    public Tour findById(String id) { return tourRepository.findById(Integer.valueOf(id)); }

    public void addTour(Tour tour) { tourRepository.save(tour); }

    public void tourRemoveById(String id) {
        tourRepository.deleteById(Integer.valueOf(id));
    }

    public List<Tour> findAll() { return tourRepository.findAll(); }

    public void removeById(Integer id) { tourRepository.deleteById(id); }

    public void update(Tour tour) {
        tourRepository.save(tour);
    }

    public Set<String> targetSet() {
        Set<String> list = new HashSet<>();
        for(Tour tour : findAll())
            if(tour.getPost())
                list.add(tour.getTarget());
        return list;
    }

    public Set<String> typeSet() {
        Set<String> list = new HashSet<>();
        for(Tour tour : findAll())
            if(tour.getPost())
                list.add(tour.getType());
        return list;
    }

    public Set<Integer> lengthSet() {
        Set<Integer> list = new HashSet<>();
        for(Tour tour : findAll())
            if(tour.getPost())
                list.add(tour.getLength());
        return list;
    }

    public List<Tour> findAccess() {
        return tourRepository.findAllByPostTrueAndPlacesIsAfter(0);
    }
}
