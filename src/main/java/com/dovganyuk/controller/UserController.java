package com.dovganyuk.controller;

import com.dovganyuk.model.User;
import com.dovganyuk.service.HotelService;
import com.dovganyuk.service.SecurityService;
import com.dovganyuk.service.UserService;
import com.dovganyuk.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    private SecurityService securityService;
    private UserValidator userValidator;
    private HotelService hotelService;

    @Autowired
    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator, HotelService hotelService) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
        this.hotelService = hotelService;
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        List<String> countries = hotelService.getAllCountries();
        model.addAttribute("countries", countries);
        return "findHotel";
    }

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }
}
