package by.bsuir.shigalo7.Controllers;

import by.bsuir.shigalo7.Entities.Stock;
import by.bsuir.shigalo7.Services.StockService;
import by.bsuir.shigalo7.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/stocks")
public class StocksController {

    @Autowired
    UserService userService;
    @Autowired
    StockService stockService;

    @GetMapping("/list")
    public String addTransport(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        List<Stock> list = stockService.findAll();
        model.addAttribute("list", list);
        return "stocks/list";
    }

    @GetMapping("/useWarehouse/{warehouseId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String useWarehouse(Model model, @PathVariable Integer warehouseId, @RequestParam Integer productId) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        Stock stock = stockService.findByWarehouseAndProduct(warehouseId, productId);
        model.addAttribute("stock", stock);
        return "/stocks/use";
    }

    @PostMapping("/useWarehouse/{warehouseId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String useWarehouseStock(Model model,
                                    @PathVariable Integer warehouseId,
                                    @RequestParam Integer productId,
                                    @RequestParam Integer quantity) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        Stock stock = stockService.findByWarehouseAndProduct(warehouseId, productId);
        stockService.useStock(stock, quantity);
        return "redirect:/warehouse/info/" + warehouseId;
    }

    @GetMapping("/getWarehouse/{warehouseId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String getWarehouse(Model model, @PathVariable Integer warehouseId, @RequestParam Integer productId) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        Stock stock = stockService.findByWarehouseAndProduct(warehouseId, productId);

        int min = Math.max(1, stock.getReorder_level() - stock.getQuantity());
        model.addAttribute("min", min);
        model.addAttribute("stock", stock);
        return "/stocks/get";
    }

    @PostMapping("/getWarehouse/{warehouseId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String getWarehouseStock(Model model,
                                    @PathVariable Integer warehouseId,
                                    @RequestParam Integer productId,
                                    @RequestParam Integer quantity) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        Stock stock = stockService.findByWarehouseAndProduct(warehouseId, productId);
        stockService.getStock(stock, quantity);
        return "redirect:/warehouse/info/" + warehouseId;
    }

    @GetMapping("/use/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String use(Model model, @PathVariable Integer id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        Stock stock = stockService.findById(id);
        model.addAttribute("stock", stock);
        return "/stocks/use";
    }

    @PostMapping("/use/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String useStock(Model model,
                           @PathVariable Integer id,
                           @RequestParam Integer quantity) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        Stock stock = stockService.findById(id);
        stockService.useStock(stock, quantity);
        return "redirect:/stocks/list";
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String get(Model model, @PathVariable Integer id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        Stock stock = stockService.findById(id);

        int min = Math.max(1, stock.getReorder_level() - stock.getQuantity());
        model.addAttribute("min", min);
        model.addAttribute("stock", stock);
        return "/stocks/get";
    }

    @PostMapping("/get/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String getStock(Model model,
                           @PathVariable Integer id,
                           @RequestParam Integer quantity) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        Stock stock = stockService.findById(id);
        stockService.getStock(stock, quantity);
        return "redirect:/stocks/list/";
    }
}
