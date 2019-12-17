package by.bsuir.shigalo7.Repositories;

import by.bsuir.shigalo7.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Product findById(Integer id);

    @Transactional
    void deleteById(Integer id);
}
