package com.epitech.bankserver.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/home")
    public String showHomeForm() {
        return "admin/home";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "admin/login";
    }

    @GetMapping("/hello")
    public String showHelloForm() {
        return "admin/login";
    }

}
