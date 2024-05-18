package com.io.rye.rye.dto;


import java.util.Set;

public class KidDto {
    private int id;
    private String username;
    private String password;
    private int balance;
    private Set<Integer> guardians;
    private Set<Integer> items;


    public Set<Integer> getItems() {
        return items;
    }

    public void setItems(Set<Integer> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Set<Integer> getGuardians() {
        return guardians;
    }

    public void setGuardians(Set<Integer> guardians) {
        this.guardians = guardians;
    }
}
