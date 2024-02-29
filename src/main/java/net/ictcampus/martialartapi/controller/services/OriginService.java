package net.ictcampus.martialartapi.controller.services;

import net.ictcampus.martialartapi.controller.repositories.OriginRepository;
import net.ictcampus.martialartapi.controller.repositories.UserRepository;
import net.ictcampus.martialartapi.model.models.Origin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service //instanziert den Service als Bean, Spring-Boot weiss wo der Service sich befindet
public class OriginService {
    private final OriginRepository originRepository;

    @Autowired//ermöglicht dependency injection, dass heisst spring-boot erzeugt automatisch die abhängigkeit
    // und instanziert das entsprechende bean zur laufzeit
    public OriginService(OriginRepository originRepository) {
        this.originRepository = originRepository;
    }

    public void deleteById(Integer id){
        originRepository.deleteById(id);
    }
    public Origin findById(Integer id){
        Optional<Origin> origin = originRepository.findById(id);
        return origin.orElseThrow(EntityNotFoundException::new);
    }
    public Iterable<Origin> findAll(){
        return originRepository.findAll();
    }
    public void update(Origin origin){
        originRepository.save(origin);
    }
    public void insert(Origin origin){
        originRepository.save(origin);
    }
    public Iterable<Origin> findByName(String name){
        return originRepository.findByName(name);
    }

}
