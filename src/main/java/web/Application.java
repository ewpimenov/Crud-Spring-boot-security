package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import web.service.RoleService;
import web.service.UserService;


@SpringBootApplication
public class Application {

    private static UserService userService;
    private static RoleService roleService;

    @Autowired
    public Application(UserService userService, RoleService roleService) {
        Application.userService = userService;
        Application.roleService = roleService;
    }


    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }
}