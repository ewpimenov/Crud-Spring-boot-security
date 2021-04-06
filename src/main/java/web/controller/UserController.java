package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.service.UserService;


@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/listUsers.html";
    }

    @GetMapping("/user")
    public String userPage() {
        return "/user.html";
    }
}
