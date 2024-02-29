package net.ictcampus.martialartapi.model.models;

import javax.persistence.*;

@Entity
@Table(name = "martialart")
//als Entität erklären und denn Table-Name hinterlegen
public class Martialart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //automatisch id erzeugen
    private Integer id_Martialart;

    @ManyToOne //Kennzeichnet m:1 Kardinalität von Martialart zu Origin
    @JoinColumn(name="origin_ID") //spezifiziert den Namen der Spalte, die als Fremdschlüssel verwendet wird
                             // ohne dass wäre nicht klar, welche origin zu welcher martial art gehört
    private Origin origin;
    private String name;













    public Integer getId_Martialart() {
        return id_Martialart;
    }

    public void setId_Martialart(Integer id_Martialart) {
        this.id_Martialart = id_Martialart;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
