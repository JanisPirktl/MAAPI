package net.ictcampus.martialartapi.controller.services;

import net.ictcampus.martialartapi.controller.repositories.MartialartRepository;
import net.ictcampus.martialartapi.model.models.Martialart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service //instanziert den Service als Bean, Spring-Boot weiss wo der Service sich befindet
public class MartialartService {
    private final MartialartRepository martialartRepository;

    @Autowired//ermöglicht dependency injection, dass heisst spring-boot erzeugt automatisch die abhängigkeit
    // und instanziert das entsprechende bean zur laufzeit
    public MartialartService(MartialartRepository martialartRepository) {
        this.martialartRepository = martialartRepository;
    }

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
        return martialartRepository.findByName(name);
    }
    public Iterable<Martialart> findByOriginName(String name) {
        return martialartRepository.findByOriginName(name);
    }

}
