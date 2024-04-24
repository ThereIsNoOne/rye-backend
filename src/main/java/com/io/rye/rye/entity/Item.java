package com.io.rye.rye.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Item {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;


    @Column(nullable = false)
    private int price;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "kid_has_item", joinColumns = @JoinColumn(name = "kid_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Kid> kid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
