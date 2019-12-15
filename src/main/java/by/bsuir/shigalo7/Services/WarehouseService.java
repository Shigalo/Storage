package by.bsuir.shigalo7.Services;

import by.bsuir.shigalo7.Entities.Warehouse;
import by.bsuir.shigalo7.Repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public List<Warehouse> findAll() { return warehouseRepository.findAll(); }


    public Warehouse create(String address) {
        Warehouse warehouse = new Warehouse(address);
        warehouseRepository.save(warehouse);
        return warehouse;
    }

    public Warehouse findById(Integer id) {
        return warehouseRepository.findById(id);
    }

    public void removeFromWarehouse(Integer warehouseId, Integer Stock) {
        Warehouse warehouse = findById(warehouseId);

    }
}
