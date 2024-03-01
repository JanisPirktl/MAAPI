package net.ictcampus.martialartapi.controller.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration//Ähnlich wie Entity Anotation bei Models, definiert diese Klasse als Konfigurationsklasse
public class ApplicationConfiguration {

    @Bean
    //benutzerdefinierte Beans zu erstellen und zu konfigurieren, anstatt sich auf die
    // automatische Komponentenerkennung und -erstellung durch Spring zu verlassen.
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }//deklaration des Passwortencoders als Bean damit er leicht in anderen Klassen verwendet werden kann.
    //BCrypt wird verwendet da er sehr sicher ist und Salting eingebaut hat

}
