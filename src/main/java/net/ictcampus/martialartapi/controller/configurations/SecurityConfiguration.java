package net.ictcampus.martialartapi.controller.configurations;

import net.ictcampus.martialartapi.controller.security.JWTAuthenticationFilter;
import net.ictcampus.martialartapi.controller.security.JWTAuthorizationFilter;
import net.ictcampus.martialartapi.controller.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static net.ictcampus.martialartapi.controller.security.SecurityConstants.API_DOCUMENTATION_URLS;
import static net.ictcampus.martialartapi.controller.security.SecurityConstants.SIGN_UP_URL;

@Configuration
@EnableWebSecurity //Bezeichnet die Sicherheitskonfiguration in der unter anderem
// Einstellungen zu JWT gemacht werden können
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService,
                                 BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    //diese methode konfiguriert, wer auf welche methode zugreifen darf
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                // Beginnt Konfiguration der Autorisierungsanforderungen
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                //Erlaubt den Zugriff auf die Sign Up URL ohne Authentifizierung
                .antMatchers(HttpMethod.GET, API_DOCUMENTATION_URLS).permitAll()
                //Erlaubt Zugriff auf die Swagger API dokumentations seiten. URLs befinden sich in Security Constants
                .anyRequest().authenticated()
                //Alle anderen Requests benötigen Authentifizierung
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Konfiguriert den AuthenticationManager, um Benutzerdetails-Service und Passwort-Encoder zu verwenden
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        // Definiert eine Bean für CORS-Konfiguration, die Standardwerte anwendet
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}