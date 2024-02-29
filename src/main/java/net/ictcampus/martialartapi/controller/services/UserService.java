package net.ictcampus.martialartapi.controller.services;

import net.ictcampus.martialartapi.controller.repositories.UserRepository;
import net.ictcampus.martialartapi.model.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findById(Integer id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(EntityNotFoundException::new);
    }
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }
    public void signUp(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void update(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }


}
