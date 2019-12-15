package by.bsuir.shigalo7.Repositories;

import by.bsuir.shigalo7.Entities.Product;
import by.bsuir.shigalo7.Entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Product findById(Integer id);

}
