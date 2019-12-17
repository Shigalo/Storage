package by.bsuir.shigalo7.Repositories;

import by.bsuir.shigalo7.Entities.Subscribe;
import by.bsuir.shigalo7.Entities.User;
import by.bsuir.shigalo7.Entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    @Transactional
    public Boolean existsByUserAndWarehouse(User user, Warehouse warehouse);

    @Transactional
    public void deleteByUserAndWarehouse(User user, Warehouse warehouse);

    List<Subscribe> findByWarehouse(Warehouse warehouse);

    List<Subscribe> findByUser(User currentUser);
}
