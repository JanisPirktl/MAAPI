package net.ictcampus.martialartapi.controller.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.ictcampus.martialartapi.controller.services.UserService;
import net.ictcampus.martialartapi.model.models.Origin;
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
    @Operation(summary = "find a User by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was found", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User was not found", content = {@Content(mediaType = "application/json",
                    schema = @Schema (implementation = User.class))})
    })
    public User findById(@PathVariable Integer id) {
        try {
            return userService.findById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @GetMapping
    @Operation(summary = "find all Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users were found", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema (implementation = User.class)))}),
            @ApiResponse(responseCode = "404", description = "Users were not found", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema (implementation = User.class)))})
    })
    public Iterable<User> findAll() {
        try {
            return userService.findAll();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PostMapping(consumes = "application/json", value = "/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User was created"),
            @ApiResponse(responseCode = "400", description = "Validation failed")
    })
    public void signUp(@Valid @RequestBody User user) {
        try {
            userService.signUp(user);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not insert user");
        }
    }

    @PutMapping
    @Operation(summary = "update a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was updated")
    })
    public void update(@Valid @RequestBody User user) {
        try {
            userService.update(user);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not update user");
        }
    }

    @DeleteMapping(path = "{id}")
    @Operation(summary = "Delete a User by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was deleted"),
            @ApiResponse(responseCode = "403", description = "Not authorized to delete a User"),
            @ApiResponse(responseCode = "400", description = "Validation failed")
    })
    public void delete(@PathVariable Integer id) {
        try {
            userService.deleteById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not delete user");
        }
    }
}
