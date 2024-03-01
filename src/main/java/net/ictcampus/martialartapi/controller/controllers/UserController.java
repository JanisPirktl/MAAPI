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

@RestController //kennzeichnet die Klasse automatisch als Controller, so dass sie automatisch HTTP-Anfragen
//bearbeitet und die Antworten direkt als JSON oder XML ausgibt.
@RequestMapping("/users") // macht, dass die Klasse sich automatisch angesprochen fühlt von Request
//mit einem |users. Klärt zuständigkeit
public class UserController {

    @Autowired//ermöglicht dependency injection, dass heisst spring-boot erzeugt automatisch die abhängigkeit
    // und instanziert das entsprechende bean zur laufzeit
    private final UserService userService;

    @Autowired//ermöglicht dependency injection, dass heisst spring-boot erzeugt automatisch die abhängigkeit
    // und instanziert das entsprechende bean zur laufzeit
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "{id}") //definiert die handler-methode für Requests mit .../{id}
    // klärt zuständigkeit / methode fühlt sich angesprochen
    @Operation(summary = "find a User by ID") //für Swagger als Methodenbeschreibung
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was found", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User was not found", content = {@Content(mediaType = "application/json",
                    schema = @Schema (implementation = User.class))})
    }) //auch für Swagger, beschreibt rückgabewert und responsecode-möglichkeiten
    public User findById(@PathVariable Integer id) {
        try {
            return userService.findById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @GetMapping //nur eine @GetMapping Methode pro Controller darf vorhanden sein!
    @Operation(summary = "find all Users")//für Swagger als Methodenbeschreibung
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users were found", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema (implementation = User.class)))}),
            @ApiResponse(responseCode = "404", description = "Users were not found", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema (implementation = User.class)))})
    })//anders als vorher muss hier ein arraySchema definiert werden da der Content des Rückgabewerts eine Liste ist.
    public Iterable<User> findAll() {
        try {
            return userService.findAll();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PostMapping(consumes = "application/json", value = "/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create a User")//für Swagger als Methodenbeschreibung
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User was created"),
            @ApiResponse(responseCode = "400", description = "Validation failed")
    })//hier muss kein Content angegeben werden da der return typ void ist
    public void signUp(@Valid @RequestBody User user) {
        try {
            userService.signUp(user);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not insert user");
        }
    }

    @PutMapping
    @Operation(summary = "update a User")//für Swagger als Methodenbeschreibung
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was updated")
    })//hier muss kein Content angegeben werden da der return typ void ist
    public void update(@Valid @RequestBody User user) {
        try {
            userService.update(user);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not update user");
        }
    }

    @DeleteMapping(path = "{id}")
    @Operation(summary = "Delete a User by ID")//für Swagger als Methodenbeschreibung
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
