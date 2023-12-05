package com.csc340.jpacruddemo;

import com.csc340.jpacruddemo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author sentini
 */
@Controller
public class AppController {

    @Autowired
    UserService userService;
/*
    @GetMapping(value = {"", "/", "/dashboard", "/home"})
    public String dashboard(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUser", name);
        return "index";
    }
*/

//    @GetMapping(value = {"", "/", "/dashboard"})
//    public String Customer() {
//        return "Customer";
//    }

    @GetMapping(value = {"", "/", "/dashboard", "/home"})
    public String dashboard(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUser", name);
        return "Customer";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/403")
    public String _403() {
        return "403";
    }
}
