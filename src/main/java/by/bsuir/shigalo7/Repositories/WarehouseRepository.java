package by.bsuir.shigalo7.Repositories;

import by.bsuir.shigalo7.Entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    public Warehouse findById(Integer id);

}
