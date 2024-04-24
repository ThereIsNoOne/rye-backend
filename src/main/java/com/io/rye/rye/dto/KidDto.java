package com.io.rye.rye.dto;

import com.io.rye.rye.entity.Guardian;

import java.util.Set;

public class KidDto {
    private int id;
    private String username;
    private String password;
    private int balance;
    private Set<GuardianDto> guardians;
    private Set<ItemDto> items;

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

    public Set<GuardianDto> getGuardians() {
        return guardians;
    }

    public void setGuardians(Set<GuardianDto> guardians) {
        this.guardians = guardians;
    }
}
