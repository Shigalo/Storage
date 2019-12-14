package by.bsuir.shigalo7.Controllers;

import by.bsuir.shigalo7.Entities.Info;
import by.bsuir.shigalo7.Entities.Tour;
import by.bsuir.shigalo7.Services.FlightService;
import by.bsuir.shigalo7.Services.InfoService;
import by.bsuir.shigalo7.Services.TourService;
import by.bsuir.shigalo7.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/tours")
public class ToursController {

    @Autowired
    UserService userService;
    @Autowired
    TourService tourService;
    @Autowired
    InfoService infoService;
    @Autowired
    FlightService flightService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getToursList(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("tourList", tourService.findAll());
        return "tours/tours";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addForm(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        return "tours/newTour";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String create(@RequestParam String target,
                         @RequestParam String name,
                         @RequestParam Integer places,
                         @RequestParam String date,
                         @RequestParam String type,
                         @RequestParam Double cost,
                         @RequestParam Integer length,
                         Model model) throws ParseException {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        Tour tour = new Tour(target, name, places, false, length, type, cost,
                new Timestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(date).getTime()));
        tourService.addTour(tour);
        return "redirect:/tours/tourInfo/" + tour.getId();
    }

    @GetMapping("/tourInfo/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addInfo(Model model, @PathVariable String id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        Tour tour = tourService.findById(id);
        model.addAttribute("tour", tour);
        model.addAttribute("infoList", infoService.findByTour(tour));
        return "tours/tourInfo";
    }

    @PostMapping("/tourInfo/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String setInfo(Model model, @PathVariable String id,
                          @RequestParam("image") MultipartFile tourImage,
                          @RequestParam String tourName,
                          @RequestParam String tourText,
                          @RequestParam String tourTarget,
                          @RequestParam Double tourCost,
                          @RequestParam Integer length,
                          @RequestParam Integer places,
                          @RequestParam String[] about,
                          @RequestParam String[] header,
                          @RequestParam Integer[] sequence,
                          @RequestParam(value = "posting", required = false) String posting,
                          @RequestParam("image") MultipartFile[] image) throws SQLException, IOException {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        Tour tour = tourService.findById(id);
        List<Info> infos = infoService.findByTour(tour);
        infoService.clear(tour);
        Blob blob;
        if(about.length != 1) {
            for (int i = 1; i < about.length; i++) {
                blob = new SerialBlob(image[i].getBytes());
                if (blob.length() == 0) { for (Info info : infos) { if (sequence[i] == info.getSequence()) { blob = info.getPicture();break; } } }
                infoService.addInfo(new Info(tourService.findById(id), i, header[i], about[i], blob));
            }
        }
        tour.setPost(posting != null);
        blob = new SerialBlob(tourImage.getBytes());
        if (blob.length() != 0) tour.setPicture(blob);
        tour.setName(tourName);
        tour.setAbout(tourText);
        tour.setLength(length);
        flightService.updateLength(tour);
        tour.setTarget(tourTarget);
        tour.setCost(tourCost);
        tour.setPlaces(tour.getPlaces() + places);

        tourService.update(tour);
        return "redirect:/tours";
    }

    @GetMapping("getTour/{id}")
    public String getTour(Model model, @PathVariable String id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        Tour tour = tourService.findById(id);
        model.addAttribute("tour", tour);
        model.addAttribute("infoList", infoService.findByTour(tour));
        return "tours/getTour";
    }

    @GetMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String remove(Model model, @PathVariable Integer id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        tourService.removeById(id);
        return "redirect:/tours";
    }
}
