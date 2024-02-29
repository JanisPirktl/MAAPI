package net.ictcampus.martialartapi.model.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "origin")
public class Origin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Origin;
    private String name;
    @OneToMany
    @JsonBackReference
    private Set<Martialart> martialarts = new HashSet<>();

    public Integer getId_Origin() {
        return id_Origin;
    }

    public void setId_Origin(Integer id_Origin) {
        this.id_Origin = id_Origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Martialart> getMartialarts() {
        return martialarts;
    }

    public void setMartialarts(Set<Martialart> martialarts) {
        this.martialarts = martialarts;
    }

}
