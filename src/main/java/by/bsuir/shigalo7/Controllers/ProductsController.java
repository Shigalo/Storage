package by.bsuir.shigalo7.Controllers;

import by.bsuir.shigalo7.Entities.Product;
import by.bsuir.shigalo7.Entities.Stock;
import by.bsuir.shigalo7.Services.ProductService;
import by.bsuir.shigalo7.Services.StockService;
import by.bsuir.shigalo7.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;
    @Autowired
    StockService stockService;

    @GetMapping("/list")
    public String addTransport(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        List<Product> list = productService.findAll();
        model.addAttribute("list", list);
        return "products/list";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String add(@RequestParam String name, @RequestParam String type, Model model) {
        return addProduct(name, type, model);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addProduct(@RequestParam String name,
                             @RequestParam String type,
                             Model model) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isLogin", userService.isLogin());
        productService.save(name, type);
        return "redirect:/products/list";
    }

    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String info(Model model, @PathVariable Integer id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        Product product = productService.findById(id);
        List<Stock> stockList = stockService.getStocksForProduct(product);
        model.addAttribute("product", product);
        model.addAttribute("stockList", stockList);

        return "products/info";
    }

    @PostMapping("/info/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveInfo(Model model, @PathVariable Integer id,
                           @RequestParam String name,
                           @RequestParam String type) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        Product product = productService.findById(id);
        productService.save(product, name, type);
        List<Stock> stockList = stockService.getStocksForProduct(product);
        model.addAttribute("product", product);
        model.addAttribute("stockList", stockList);

        return "redirect:/products/info/" + id;
    }

    @GetMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String remove(Model model, @PathVariable Integer id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        productService.deleteById(id);
        return "redirect:/products/list";
    }
}
