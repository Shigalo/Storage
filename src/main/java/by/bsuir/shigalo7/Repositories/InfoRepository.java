package by.bsuir.shigalo7.Repositories;

import by.bsuir.shigalo7.Entities.Info;
import by.bsuir.shigalo7.Entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InfoRepository extends JpaRepository<Info, Long> {
//    public Flight findByName(String name);

    public Info findById(Integer id);

    @Transactional
    public void deleteById(Integer id);

    public List<Info> findByTour(Tour tour);

    @Transactional
    public void deleteByTour(Tour tour);

/*    @Transactional
    @Modifying
//    @Query("update Flight set complete = true where id = ?1")
    @Query("update Flight r set r.complete = true where r.id = ?1")
    public void setComplete(Integer id);*/

}
