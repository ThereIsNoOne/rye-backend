package com.io.rye.rye.entity;

import jakarta.persistence.*;

@Entity
public class Admin {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column(unique=true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
