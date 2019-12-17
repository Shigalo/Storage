package by.bsuir.shigalo7.Services;

import by.bsuir.shigalo7.Entities.Product;
import by.bsuir.shigalo7.Repositories.ProductRepository;
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

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    public void save(String name, String type) {
        Product product = new Product(name, type);
        productRepository.save(product);
    }

    public void save(Product product, String name, String type) {
        product.setName(name);
        product.setType(type);
        productRepository.save(product);
    }
}
