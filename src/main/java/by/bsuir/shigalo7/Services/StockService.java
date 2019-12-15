package by.bsuir.shigalo7.Services;

import by.bsuir.shigalo7.Entities.Product;
import by.bsuir.shigalo7.Entities.Stock;
import by.bsuir.shigalo7.Entities.Warehouse;
import by.bsuir.shigalo7.Repositories.StockRepository;
import by.bsuir.shigalo7.Repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;
    @Autowired
    ProductService productService;

    public List<Stock> findAll() { return stockRepository.findAll(); }

    public Stock findById(Integer id) {
        return stockRepository.findById(id);
    }

    public List<Stock> findByWarehouse(Warehouse warehouse) { return stockRepository.findByWarehouse(warehouse); }

    public List<Product> findForWarehouse(Warehouse warehouse) {
        List<Product> productList = productService.findAll();
        List<Stock> stockList = findByWarehouse(warehouse);
        for(Stock stock : stockList)
            productList.remove(stock.getProduct());
        return productList;
    }

    public void addProductToWarehouse(String productData, Warehouse warehouse, Integer level) {
        String productId = productData.split(" ")[0];
        Product product = productService.findById(productId);
        Stock stock = new Stock(level, 0, product, warehouse);
        stockRepository.save(stock);
    }

    public void delete(Integer id) {
        stockRepository.deleteById(id);
    }
}
