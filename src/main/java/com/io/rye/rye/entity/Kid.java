package com.io.rye.rye.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Kid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int balance;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "guardian_kid_map", joinColumns = @JoinColumn(name = "guardian_id"), inverseJoinColumns = @JoinColumn(name = "kid_id"))
    private Set<Guardian> guardians;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "kid_has_item", joinColumns = @JoinColumn(name = "kid_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public Set<Guardian> getGuardians() {
        return guardians;
    }

    public void setGuardians(Set<Guardian> guardians) {
        this.guardians = guardians;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
