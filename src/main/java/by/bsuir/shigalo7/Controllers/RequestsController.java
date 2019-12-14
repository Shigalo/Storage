package by.bsuir.shigalo7.Controllers;

import by.bsuir.shigalo7.Entities.Flight;
import by.bsuir.shigalo7.Entities.Tour;
import by.bsuir.shigalo7.Services.FlightService;
import by.bsuir.shigalo7.Services.RequestService;
import by.bsuir.shigalo7.Services.TourService;
import by.bsuir.shigalo7.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/requests")
public class RequestsController {

    @Autowired
    UserService userService;

    @Autowired
    TourService tourService;
    @Autowired
    FlightService flightService;
    @Autowired
    RequestService requestService;

    @GetMapping("/addRequest/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String addTransport(Model model, @PathVariable String id) {
        model.addAttribute("isLogin", userService.isLogin());
        Tour tour = tourService.findById(id);
        List<Flight> flightList = flightService.findByTour(tour);
        model.addAttribute("tour", tourService.findById(id));
        model.addAttribute("flightList", flightList);
        model.addAttribute("flightService", flightService);

        return "requests/newRequest";
    }

    @PostMapping("/addRequest/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAnyAuthority('USER')")
    public String addTransportData(@RequestParam Integer places,
                                   @RequestParam String questions,
                                   @RequestParam String flightId,
                                   @RequestParam String cost,
                                   @RequestParam String flights,
                                   Model model, @PathVariable String id) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isLogin", userService.isLogin());
        if(flights.equals("without")) flightId = null;

        requestService.addRequest(places, questions, flightId, Double.valueOf(cost), id);
        return "redirect:/";
    }

    @GetMapping("/requests")
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
    }
}
