package by.bsuir.shigalo7.Services;

import by.bsuir.shigalo7.Entities.Subscribe;
import by.bsuir.shigalo7.Entities.User;
import by.bsuir.shigalo7.Entities.Warehouse;
import by.bsuir.shigalo7.Repositories.SubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscribeService {

    @Autowired
    SubscribeRepository subscribeRepository;
    @Autowired
    WarehouseService warehouseService;
    @Autowired
    UserService userService;

    public boolean isSubscribe(Warehouse warehouse) {
        User user = userService.getCurrentUser();
        return subscribeRepository.existsByUserAndWarehouse(user, warehouse);
    }

    public void subscribe(Integer id) {
        Warehouse warehouse = warehouseService.findById(id);
        User user = userService.getCurrentUser();
        if(isSubscribe(warehouse)) subscribeRepository.deleteByUserAndWarehouse(user, warehouse);
        else subscribeRepository.save(new Subscribe(user, warehouse));
    }

    public List<Subscribe> findByWarehouse(Warehouse warehouse) {
        return subscribeRepository.findByWarehouse(warehouse);
    }

    public List<Subscribe> findByUser() {
        return subscribeRepository.findByUser(userService.getCurrentUser());
    }
}
