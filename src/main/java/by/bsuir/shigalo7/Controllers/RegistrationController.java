package by.bsuir.shigalo7.Controllers;

import by.bsuir.shigalo7.Entities.Massage;
import by.bsuir.shigalo7.Entities.Role;
import by.bsuir.shigalo7.Entities.User;
import by.bsuir.shigalo7.Repositories.UserRepository;
import by.bsuir.shigalo7.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping
    public String setForm(Model  model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("massage", Massage.getInstance().getStr());
        return "accountWork/registration";
    }

    @PostMapping
    public String getForm(@RequestParam String username,
                          @RequestParam String surname,
                          @RequestParam String phone,
                          @RequestParam String password,
                          @RequestParam String passwordConfirm,
                          @RequestParam String mail,
                          Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        if(!password.equals(passwordConfirm)) {
            model.addAttribute("error", "Пароли не совпадают!");
            model.addAttribute("username", username);
            model.addAttribute("surname", surname);
            model.addAttribute("phone", phone);
            model.addAttribute("password", password);
            model.addAttribute("mail", mail);
            return "accountWork/registration";
        }
        User user = new User(username, password, true, surname, phone, mail);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        Massage.getInstance().setStr("new");
        return "redirect:/login";
    }
}
