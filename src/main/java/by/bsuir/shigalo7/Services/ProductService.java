package by.bsuir.shigalo7.Services;

import by.bsuir.shigalo7.Entities.Product;
import by.bsuir.shigalo7.Entities.Stock;
import by.bsuir.shigalo7.Entities.Warehouse;
import by.bsuir.shigalo7.Repositories.ProductRepository;
import by.bsuir.shigalo7.Repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findAll() { return productRepository.findAll(); }

    public Product findById(Integer id) {
        return productRepository.findById(id);
    }

    public Product findById(String productId) {
        Integer id = Integer.valueOf(productId);
        return productRepository.findById(id);
    }
}
