package net.ictcampus.martialartapi.controller.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration//Bezeichnet eine Klasse in der Beans erstellt werden dürfen, die von Spring verwaltet werden.
public class ApplicationConfiguration {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }//deklaration des Passwortencoders als Bean damit er leicht in anderen Klassen verwendet werden kann.
    //BCrypt wird verwendet da er sehr sicher ist und Salting eingebaut hat

}
