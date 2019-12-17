package by.bsuir.shigalo7.Controllers;

import by.bsuir.shigalo7.Entities.Subscribe;
import by.bsuir.shigalo7.Services.SubscribeService;
import by.bsuir.shigalo7.Services.UserService;
import by.bsuir.shigalo7.Services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    WarehouseService warehouseService;
    @Autowired
    SubscribeService subscribeService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("warehouses", warehouseService.findAll());
        return "homepage";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String profile(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        List<Subscribe> subscribeList = subscribeService.findByUser();
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("subscribeList", subscribeList);
        return "accountWork/profile";
    }

    @GetMapping("/userRemove")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String remove(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        userService.userRemove();
        return "redirect:/";
    }
}
