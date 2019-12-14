package by.bsuir.shigalo7.Controllers;

import by.bsuir.shigalo7.Entities.Flight;
import by.bsuir.shigalo7.Entities.Tour;
import by.bsuir.shigalo7.Services.FlightService;
import by.bsuir.shigalo7.Services.TourService;
import by.bsuir.shigalo7.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
@RequestMapping("/flights")
public class FlightsController {

    @Autowired
    UserService userService;
    @Autowired
    FlightService flightService;
    @Autowired
    TourService tourService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String viewFlights(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("flightList", flightService.findAll());
        return "flights/flights";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addForm(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("tourList", tourService.findAll());
        return "flights/newFlight";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String create(@RequestParam String out,
                         @RequestParam Double cost,
                         @RequestParam String tour,
                         Model model) throws ParseException {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        Tour buf = tourService.findById(tour.split(":")[0]);
        Flight flight = new Flight(out, cost, buf.getDate().plusDays(buf.getLength()));

        flight.setTour(buf);
        flightService.addFlight(flight);
        return "redirect:/flights";
    }

    @GetMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String remove(Model model, @PathVariable Integer id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        flightService.removeById(id);
        return "redirect:/flights";
    }
}
