package by.bsuir.shigalo7.Repositories;

import by.bsuir.shigalo7.Entities.Stock;
import by.bsuir.shigalo7.Entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    public Stock findById(Integer id);
    
    public List<Stock> findByWarehouse(Warehouse warehouse);

    @Transactional
    void deleteById(Integer id);
}
