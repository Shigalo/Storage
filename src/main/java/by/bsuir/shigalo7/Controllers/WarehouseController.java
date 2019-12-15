package by.bsuir.shigalo7.Controllers;

import by.bsuir.shigalo7.Entities.Product;
import by.bsuir.shigalo7.Entities.Stock;
import by.bsuir.shigalo7.Entities.Warehouse;
import by.bsuir.shigalo7.Services.StockService;
import by.bsuir.shigalo7.Services.UserService;
import by.bsuir.shigalo7.Services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    UserService userService;
    @Autowired
    WarehouseService warehouseService;
    @Autowired
    StockService stockService;

    @PostMapping("/add")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public String create(@RequestParam String address, Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        Warehouse warehouse = warehouseService.create(address);
        return "redirect:/warehouse/warehouseInfo/" + warehouse.getId();
    }

    @GetMapping("/warehouseInfo/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public String getInfo(Model model, @PathVariable Integer id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        Warehouse warehouse = warehouseService.findById(id);
        List<Stock> stockList = stockService.findByWarehouse(warehouse);
        List<Product> productList = stockService.findForWarehouse(warehouse);
        model.addAttribute("warehouse", warehouse);
        model.addAttribute("stockList", stockList);
        model.addAttribute("productList", productList);

        return "warehouse/warehouseInfo";
    }

    @PostMapping("/addProduct/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public String addProduct(Model model,
                             @RequestParam String productData,
                             @RequestParam Integer level,
                             @PathVariable Integer id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        Warehouse warehouse = warehouseService.findById(id);
        stockService.addProductToWarehouse(productData, warehouse, level);


        return "redirect:/warehouse/warehouseInfo/"+id;
    }

    @GetMapping("/remove/{warehouseId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public String remove(Model model, @PathVariable Integer warehouseId, @RequestParam Integer id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        stockService.delete(id);
        return "redirect:/warehouse/warehouseInfo/"+warehouseId;
    }

    /*@GetMapping("/tourInfo/{id}")
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
    }*/
}
