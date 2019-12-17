package by.bsuir.shigalo7.Services;

import by.bsuir.shigalo7.Entities.Product;
import by.bsuir.shigalo7.Entities.Stock;
import by.bsuir.shigalo7.Entities.Warehouse;
import by.bsuir.shigalo7.Repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;
    @Autowired
    ProductService productService;
    @Autowired
    WarehouseService warehouseService;

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

    public void addProductToWarehouse(String productData, Warehouse warehouse, Integer level, Integer initialStock) {
        String productId = productData.split(" ")[0];
        Product product = productService.findById(productId);
        Stock stock = new Stock(level, initialStock, product, warehouse);
        stockRepository.save(stock);
    }

    public void delete(Integer id) {
        stockRepository.deleteById(id);
    }

    public List<Stock> getStocksForProduct(Product product) {
        return stockRepository.findByProduct(product);
    }

    public Stock findByWarehouseAndProduct(Integer warehouseId, Integer productId) {
        Warehouse warehouse = warehouseService.findById(warehouseId);
        Product product = productService.findById(productId);
        return stockRepository.findByWarehouseAndProduct(warehouse, product);
    }

    @Autowired
    EmailSendingService sendingService;

    public void useStock(Stock stock, Integer quantity) {
        stock.decrease(quantity);

        if(stock.getQuantity() <= stock.getReorder_level())
            sendingService.sendMessages(stock);

        stockRepository.save(stock);
    }

    public void getStock(Stock stock, Integer quantity) {
        stock.replenishment(quantity);
        stockRepository.save(stock);
    }
}
