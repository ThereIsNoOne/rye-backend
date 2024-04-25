package com.io.rye.rye.dto;

import java.util.Set;

public class ItemDto {
    private int id;
    private int price;
    private Set<Integer> kids;

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

    public Set<Integer> getKids() {
        return kids;
    }

    public void setKids(Set<Integer> kids) {
        this.kids = kids;
    }
}
