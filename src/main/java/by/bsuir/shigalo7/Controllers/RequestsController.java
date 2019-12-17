package by.bsuir.shigalo7.Controllers;

import by.bsuir.shigalo7.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class RequestsController {

    @Autowired
    UserService userService;

    @GetMapping("/add")
//    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String addTransport(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());



        return "requests/newRequest";
    }

    @PostMapping("/add")
//    @PreAuthorize("hasAuthority('ADMIN') || hasAnyAuthority('USER')")
    public String addTransportData(@RequestParam Integer places,
                                   @RequestParam String questions,
                                   @RequestParam String flightId,
                                   @RequestParam String cost,
                                   @RequestParam String flights,
                                   Model model) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isLogin", userService.isLogin());

        return "redirect:/";
    }

  /*  @GetMapping("/requests")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String requests(Model model) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("requestsList", requestService.findAll());

        return "requests/requests";
    }

    @GetMapping("/confirm/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String requests(Model model, @PathVariable String id) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isLogin", userService.isLogin());
        requestService.confirm(id);

        return "redirect:/requests/requests";
    }*/
}
