package net.ictcampus.martialartapi.controller.controllers;

import net.ictcampus.martialartapi.controller.services.UserService;
import net.ictcampus.martialartapi.model.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "{id}")
    public User findById(@PathVariable Integer id) {
        try {
            return userService.findById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @GetMapping
    public Iterable<User> findAll() {
        try {
            return userService.findAll();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PostMapping(consumes = "application/json", value = "/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@Valid @RequestBody User user) {
        try {
            userService.signUp(user);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not insert user");
        }
    }

    @PutMapping
    public void update(@Valid @RequestBody User user) {
        try {
            userService.update(user);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not update user");
        }
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Integer id) {
        try {
            userService.deleteById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not delete user");
        }
    }
}
