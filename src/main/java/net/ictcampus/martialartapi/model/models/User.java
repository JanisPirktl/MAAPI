package net.ictcampus.martialartapi.model.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
//als Entität erklären und denn Table-Name hinterlegen
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //automatisch id erzeugen
    private Integer id_User;

    @NotNull(message = "name is required") // darf nicht nichts sein
    @NotBlank(message = "username can't be empty") // darf nicht leer sein
    private String username;

    private String name;
    private String surname;
    private Integer martialart_Id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //bedeutet, dass das annotierte Feld oder die annotierte Methode nur für die
    //Deserialisierung von eingehendem JSON in ein Java-Objekt verwendet wird, nicht aber
    //für die Serialisierung eines Java-Objekts zurück in JSON. Mit anderen Worten, das Feld wird
    //beim Schreiben in die Datenquelle berücksichtigt, aber beim Lesen ignoriert.
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
