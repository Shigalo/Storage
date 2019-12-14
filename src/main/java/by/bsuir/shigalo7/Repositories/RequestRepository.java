package by.bsuir.shigalo7.Repositories;

import by.bsuir.shigalo7.Entities.Request;
import by.bsuir.shigalo7.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
//    public Request findByName(String name);

    public Request findById(Integer id);

    @Transactional
    public void deleteById(Integer id);

    public List<Request> findByUser(User user);

/*    @Transactional
    @Modifying
//    @Query("update Request set complete = true where id = ?1")
    @Query("update Request r set r.complete = true where r.id = ?1")
    public void setComplete(Integer id);*/

}
