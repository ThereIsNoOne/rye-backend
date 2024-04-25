package com.io.rye.rye.dto;

import java.util.Set;

public class GuardianDto {

    private int id;
    private String username;
    private String password;
    private String email;
    private String familyMember;
    private Set<Integer> kids;

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

    public String getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(String familyMember) {
        this.familyMember = familyMember;
    }

    public Set<Integer> getKids() {
        return kids;
    }

    public void setKids(Set<Integer> kids) {
        this.kids = kids;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
