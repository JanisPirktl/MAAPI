package net.ictcampus.martialartapi.controller.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.ictcampus.martialartapi.controller.services.OriginService;
import net.ictcampus.martialartapi.model.models.Martialart;
import net.ictcampus.martialartapi.model.models.Origin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/origins")
public class OriginController {

    @Autowired
    private final OriginService originService;

    @Autowired
    public OriginController(OriginService originService) {
        this.originService = originService;
    }

    @GetMapping(path = "{id}")
    @Operation(summary = "find a Country of Origin by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country was found", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Origin.class))}),
            @ApiResponse(responseCode = "404", description = "Country was not found", content = {@Content(mediaType = "application/json",
                    schema = @Schema (implementation = Origin.class))})
    })
    public Origin findById(@PathVariable Integer id) {
        try {
            return originService.findById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Origin not found");
        }
    }



    @GetMapping
    @Operation(summary = "find a Country of Origin by Name or find all Countries in the Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country of Origin was found", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema (implementation = Origin.class)))}),
            @ApiResponse(responseCode = "404", description = "Country of Origin was not found", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema (implementation = Origin.class)))})
    })
    public Iterable<Origin> findByName(@RequestParam (required = false) String name) {
        try {
            if (name != null){
                return originService.findByName(name);
            }else{
                return originService.findAll();
            }

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Origin not found");
        }
    }
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create a Country of Origin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Country of Origin was created"),
            @ApiResponse(responseCode = "403", description = "Not authorized to create a Country of Origin"),
            @ApiResponse(responseCode = "400", description = "Validation failed")
    })
    public void insert(@Valid @RequestBody Origin origin) {
        try {
            originService.insert(origin);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not insert origin");
        }
    }

    @PutMapping
    @Operation(summary = "update a Country of Origin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country of Origin was updated")
    })
    public void update(@Valid @RequestBody Origin origin) {
        try {
            originService.update(origin);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not update origin");
        }
    }

    @DeleteMapping(path = "{id}")
    @Operation(summary = "Delete a Country of Origin by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country of Origin was deleted"),
            @ApiResponse(responseCode = "403", description = "Not authorized to delete a Country of Origin"),
            @ApiResponse(responseCode = "400", description = "Validation failed")
    })
    public void delete(@PathVariable Integer id) {
        try {
            originService.deleteById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not delete origin");
        }
    }
}
