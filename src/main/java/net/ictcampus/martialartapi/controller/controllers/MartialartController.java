package net.ictcampus.martialartapi.controller.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.ictcampus.martialartapi.controller.services.MartialartService;
import net.ictcampus.martialartapi.model.models.Martialart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/martialarts")
public class MartialartController {

    @Autowired
    private final MartialartService martialartService;

    @Autowired
    public MartialartController(MartialartService martialartService) {
        this.martialartService = martialartService;
    }

    @GetMapping(path = "{id}")
    @Operation(summary = "find a Martialart by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Martialart was found", content = {@Content(mediaType = "application/json",
                    schema = @Schema (implementation = Martialart.class))}),
            @ApiResponse(responseCode = "404", description = "Martialart was not found", content = {@Content(mediaType = "application/json",
                    schema = @Schema (implementation = Martialart.class))})
    })
    public Martialart findById(@PathVariable Integer id) {
        try {
            return martialartService.findById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Martial art not found");
        }
    }



    @GetMapping
    @Operation(summary = "find a Martialart by Name or Country of Origin or find all Martialarts in the Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Martialart was found", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema (implementation = Martialart.class)))}),
            @ApiResponse(responseCode = "404", description = "Martialart was not found", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema (implementation = Martialart.class)))})
    })
    public Iterable<Martialart> findByNameAndOriginName(@RequestParam(required = false) String name, @RequestParam(required = false) String originName){
        try{
            if (name != null){
                return martialartService.findByName(name);
            } else if (originName != null) {
                return martialartService.findByOriginName(originName);
            } else {
                return martialartService.findAll();
            }
        }catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Martialart not found");
        }

    }


    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create a Martialart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Martialart was created"),
            @ApiResponse(responseCode = "403", description = "Not authorized to create a Martialart"),
            @ApiResponse(responseCode = "400", description = "Validation failed")
    })
    public void insert(@Valid @RequestBody Martialart martialart) {
        try {
            martialartService.insert(martialart);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not insert martial art");
        }
    }

    @PutMapping
    @Operation(summary = "update a Martialart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Martialart was updated")
    })
    public void update(@Valid @RequestBody Martialart martialart) {
        try {
            martialartService.update(martialart);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not update martial art");
        }
    }

    @DeleteMapping(path = "{id}")
    @Operation(summary = "Delete a Martialart by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Martialart was deleted"),
            @ApiResponse(responseCode = "403", description = "Not authorized to delete a Martialart"),
            @ApiResponse(responseCode = "400", description = "Validation failed")
    })
    public void delete(@PathVariable Integer id) {
        try {
            martialartService.deleteById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not delete martial art");
        }
    }
}
