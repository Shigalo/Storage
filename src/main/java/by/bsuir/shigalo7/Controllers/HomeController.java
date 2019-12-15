package by.bsuir.shigalo7.Controllers;

import by.bsuir.shigalo7.Entities.Massage;
import by.bsuir.shigalo7.Services.EmailSendingService;
import by.bsuir.shigalo7.Services.UserService;
import by.bsuir.shigalo7.Services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    WarehouseService warehouseService;
//    @Autowired
//    EmailSendingService sendingService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("warehouses", warehouseService.findAll());
        return "homepage";
    }

    @GetMapping("/test")
    public String test(Model model) throws Exception {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
//        sendingService.sendMessage();

        return "redirect:/";
    }
}
