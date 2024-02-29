package net.ictcampus.martialartapi.controller.controllers;

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
    public Martialart findById(@PathVariable Integer id) {
        try {
            return martialartService.findById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Martial art not found");
        }
    }


    @GetMapping
    public Iterable<Martialart> findByName(@RequestParam(required = false) String name){
        try{
            if(name != null){
                return martialartService.findByName(name);
            }else{
                return martialartService.findAll();
            }

        }catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Martialart not found");
        }

    }


    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@Valid @RequestBody Martialart martialart) {
        try {
            martialartService.insert(martialart);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not insert martial art");
        }
    }

    @PutMapping
    public void update(@Valid @RequestBody Martialart martialart) {
        try {
            martialartService.update(martialart);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not update martial art");
        }
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Integer id) {
        try {
            martialartService.deleteById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not delete martial art");
        }
    }
}
