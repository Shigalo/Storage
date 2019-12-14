package by.bsuir.shigalo7.Controllers;

import by.bsuir.shigalo7.Services.FlightService;
import by.bsuir.shigalo7.Services.RequestService;
import by.bsuir.shigalo7.Services.TourService;
import by.bsuir.shigalo7.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    TourService tourService;
    @Autowired
    RequestService requestService;
    @Autowired
    FlightService flightService;


    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("tourList", tourService.findAccess());
        model.addAttribute("targetSet", tourService.targetSet());
        model.addAttribute("lengthSet", tourService.lengthSet());
        model.addAttribute("typeSet", tourService.typeSet());

        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        return "homepage";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "error";
    }
}
