package net.ictcampus.martialartapi.model.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "origin")
//als Entität erklären und denn Table-Name hinterlegen
public class Origin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //automatisch id erzeugen
    private Integer id_Origin;
    private String name;
    @OneToMany //1:m Kardinalität wird in Set-Struktur gespeichert
                //erlaubt, dass Origin mehrfach vorkommen darf in Set<Martialart>
    @JsonBackReference //verhindert zyklische Referenzen
    //markiert die Seite der Beziehung, die ignoriert werden soll (hier Origin)
    private Set<Martialart> martialarts = new HashSet<>(); //speichert dazugehörige Martial Arts (Mehrzahl)











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
