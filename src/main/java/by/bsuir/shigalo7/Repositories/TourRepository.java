package by.bsuir.shigalo7.Repositories;

import by.bsuir.shigalo7.Entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {

    public Tour findById(Integer id);

    @Transactional
    public void deleteById(Integer id);

    public List<Tour> findAllByPostTrueAndPlacesIsAfter(int places);

}
