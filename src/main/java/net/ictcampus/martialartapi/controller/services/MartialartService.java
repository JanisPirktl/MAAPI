package net.ictcampus.martialartapi.controller.services;

import net.ictcampus.martialartapi.controller.repositories.MartialartRepository;
import net.ictcampus.martialartapi.model.models.Martialart;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
@Service

public class MartialartService {
    private MartialartRepository martialartRepository;

    public void deleteById(Integer id){
        martialartRepository.deleteById(id);
    }
    public Martialart findById(Integer id){
        Optional<Martialart> martialart = martialartRepository.findById(id);
        return martialart.orElseThrow(EntityNotFoundException::new);
    }
    public Iterable<Martialart> findAll(){
        return martialartRepository.findAll();
    }
    public void update(Martialart martialart){
        martialartRepository.save(martialart);
    }
    public void insert(Martialart martialart){
        martialartRepository.save(martialart);
    }
    public Iterable<Martialart> findByName(String name){
        return martialartRepository.findByName;
    }

}
