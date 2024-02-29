package net.ictcampus.martialartapi.model.models;

public class User {
    private Integer id_User;
    private String username;

    private String name;
    private String surname;
    private Integer martialart_Id;

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
}
