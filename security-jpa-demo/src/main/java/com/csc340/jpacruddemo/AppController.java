package com.csc340.jpacruddemo;

import com.csc340.jpacruddemo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.csc340.jpacruddemo.user.User;


@Controller
public class AppController {
    @Autowired
    UserService userService;

    @GetMapping(value = {"", "/", "/dashboard", "/home"})
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // 获取当前登录的用户名
        String role = auth.getAuthorities().toString();
        model.addAttribute("currentUser", username);

        if (role.contains("ADMIN")) {
            return "index";
        } else if (role.contains("BANKER")) {
            return "Banker";
        } else if (role.contains("USER")) {
            User user = userService.findByUserName(username);
            model.addAttribute("accountNumber", user.getAccountNumber());
            model.addAttribute("accountBalance", user.getBalance());
            return "Customer";
        }

        return "login";
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