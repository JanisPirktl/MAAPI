package net.ictcampus.martialartapi.controller.controllers;

import net.ictcampus.martialartapi.controller.services.OriginService;
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
    public Origin findById(@PathVariable Integer id) {
        try {
            return originService.findById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Origin not found");
        }
    }

    @GetMapping
    public Iterable<Origin> findAll() {
        try {
            return originService.findAll();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Origin not found");
        }
    }
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@Valid @RequestBody Origin origin) {
        try {
            originService.insert(origin);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not insert origin");
        }
    }

    @PutMapping
    public void update(@Valid @RequestBody Origin origin) {
        try {
            originService.update(origin);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not update origin");
        }
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Integer id) {
        try {
            originService.deleteById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not delete origin");
        }
    }
}
