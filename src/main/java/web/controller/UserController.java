package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.service.RoleService;
import web.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String currentUserRole(Model model, Principal principal) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("role", roleService.getAllRolesByName());
        model.addAttribute("user", principal.getName());
        return "/user";
    }
}