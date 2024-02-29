package net.ictcampus.martialartapi.controller.services;

import net.ictcampus.martialartapi.controller.repositories.UserRepository;
import net.ictcampus.martialartapi.model.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

//Diese Klasse lädt Benutzerdetails aus der Datenbank zur Authentifizierung durch Spring Security,
//indem sie den
//Benutzernamen verwendet. Bei Nichtauffinden eines Benutzers
//wird eine UsernameNotFoundException geworfen.

@Service //instanziert den Service als Bean, Spring-Boot weiss wo der Service sich befindet
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                emptyList());
    }
}
