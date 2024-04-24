package com.io.rye.rye.dto;

import java.util.Set;

public class GuardianDto {

    private String id;
    private String username;
    private String password;
    private String familyMember;
    private Set<KidDto> kids;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Set<KidDto> getKids() {
        return kids;
    }

    public void setKids(Set<KidDto> kids) {
        this.kids = kids;
    }
}
