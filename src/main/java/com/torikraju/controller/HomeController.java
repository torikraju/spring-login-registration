package com.torikraju.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String showPage(Principal principal, Model model) {
        model.addAttribute("user", principal.toString());
        return "home";
    }

}
