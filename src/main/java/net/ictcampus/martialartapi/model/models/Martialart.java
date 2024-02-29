package net.ictcampus.martialartapi.model.models;

import javax.persistence.*;

@Entity
@Table(name = "martialart")
public class Martialart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Martialart;
    @ManyToOne
    @JoinColumn(name="origin_ID")
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
