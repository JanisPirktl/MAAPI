package net.ictcampus.martialartapi.model.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_User;

    @NotNull(message = "name is required")
    @NotBlank(message = "username can't be empty")
    private String username;
    private String name;
    private String surname;
    private Integer martialart_Id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    @Length(min = 6, max = 255)
    private String password;

    public Integer getId_User() {
        return id_User;
    }

    public void setId_User(Integer id_User) {
        this.id_User = id_User;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getMartialart_Id() {
        return martialart_Id;
    }

    public void setMartialart_Id(Integer martialart_Id) {
        this.martialart_Id = martialart_Id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
