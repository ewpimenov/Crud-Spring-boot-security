package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import web.service.RoleService;
import web.service.UserService;

@SpringBootApplication
public class Application {

    private static UserService userService;

    @Autowired
    public Application(UserService userService) {
        Application.userService = userService;
    }


    public static void main(String[] args) {


        SpringApplication.run(Application.class, args);
      //  System.out.println(userService.getUser(199).getPassword());
    }
}