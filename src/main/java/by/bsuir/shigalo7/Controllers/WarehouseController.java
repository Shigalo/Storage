package by.bsuir.shigalo7.Controllers;

import by.bsuir.shigalo7.Entities.Product;
import by.bsuir.shigalo7.Entities.Stock;
import by.bsuir.shigalo7.Entities.Warehouse;
import by.bsuir.shigalo7.Services.StockService;
import by.bsuir.shigalo7.Services.SubscribeService;
import by.bsuir.shigalo7.Services.UserService;
import by.bsuir.shigalo7.Services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import by.bsuir.shigalo7.Services.SubscribeService;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    UserService userService;
    @Autowired
    WarehouseService warehouseService;
    @Autowired
    StockService stockService;
    @Autowired
    SubscribeService subscribeService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        List<Warehouse> list = warehouseService.findAll();
        model.addAttribute("list", list);
        return "warehouse/list";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String create(@RequestParam String address, Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        Warehouse warehouse = warehouseService.create(address);
        return "redirect:/warehouse/info/" + warehouse.getId();
    }

    @GetMapping("/subscribe/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String subscribe(Model model, @PathVariable Integer id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        subscribeService.subscribe(id);
        return "redirect:/warehouse/info/" + id;
    }

    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String getInfo(Model model, @PathVariable Integer id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        Warehouse warehouse = warehouseService.findById(id);
        List<Stock> stockList = stockService.findByWarehouse(warehouse);
        List<Product> productList = stockService.findForWarehouse(warehouse);
        model.addAttribute("warehouse", warehouse);
        model.addAttribute("stockList", stockList);
        model.addAttribute("productList", productList);
        boolean subscribe = userService.isLogin() && subscribeService.isSubscribe(warehouse);
        model.addAttribute("subscribe", subscribe);

        return "warehouse/info";
    }

    @PostMapping("/addProduct/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addProduct(Model model,
                             @RequestParam String productData,
                             @RequestParam Integer level,
                             @RequestParam Integer initialStock,
                             @PathVariable Integer id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        Warehouse warehouse = warehouseService.findById(id);
        stockService.addProductToWarehouse(productData, warehouse, level, initialStock);
        return "redirect:/warehouse/info/"+id;
    }

    @GetMapping("/remove/{warehouseId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String remove(Model model, @PathVariable Integer warehouseId, @RequestParam Integer id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        stockService.delete(id);
        return "redirect:/warehouse/info/"+warehouseId;
    }

    @GetMapping("/use/{warehouseId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String use(Model model, @PathVariable Integer warehouseId, @RequestParam Integer id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        return "redirect:/stocks/useWarehouse/" + warehouseId + "?productId=" + id;
    }

    @GetMapping("/get/{warehouseId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String get(Model model, @PathVariable Integer warehouseId, @RequestParam Integer id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        return "redirect:/stocks/getWarehouse/" + warehouseId + "?productId=" + id;
    }

    @GetMapping("/removeWarehouse/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String removeWarehouse(Model model, @PathVariable Integer id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        warehouseService.delete(id);
        return "redirect:/warehouse/list";
    }
}
