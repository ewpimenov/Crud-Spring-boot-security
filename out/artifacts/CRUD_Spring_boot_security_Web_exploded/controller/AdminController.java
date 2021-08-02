package web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/admin")
@Transactional
public class AdminController {

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    public AdminController(RoleService roleService, UserService userService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<User> users() {
        return userService.getAllUsers();
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<User> get(@PathVariable(name = "id") int id) {
        try {
            User user = userService.getUser(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public User add(@RequestBody User user, @RequestBody String[] role) {
        user.setRoles(roleService.getRolesByName(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
         return userService.addUser(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable(name = "id") int id, @RequestBody User user, @RequestBody String[] role) {
        try {
            user.setRoles(roleService.getRolesByName(role));
            User userFromDB = userService.getUser(id);
            String oldPassword = userFromDB.getPassword();
            if (!user.getPassword().equals(oldPassword)) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userService.updateUser(user);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
