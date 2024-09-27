package com.codegym.controller;

import com.codegym.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping({"/", "/welcome"})
    public String welcomePage(Model model) {
        model.addAttribute("title", "welcome");
        model.addAttribute("message", "This is the welcome page!");
        return "/welcomePage";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, Principal principal) {
        User loggedInUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loggedInUser);
        model.addAttribute("userInfo", userInfo);
        return "/adminPage";
    }

    @GetMapping("/logoutSuccessful")
    public String pagePostedSuccessfully(Model model) {
        model.addAttribute("title", "log out");
        return "/logoutSuccessfulPage";
    }

    @GetMapping("/userinfo")
    public String userinfo(Model model, Principal principal) {
        String username = principal.getName();
        System.out.println("User Name: " + username);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "/userInfoPage";
    }

    @GetMapping("/403")
    public String error(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userinfo", userInfo);

            String message = "Hi " + principal.getName() + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }

        return "/403Page";
    }
}
