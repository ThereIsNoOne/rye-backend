package com.io.rye.rye.entity;

import jakarta.persistence.*;

@Entity
public class MascotDetails {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column(nullable = false)
    private String color;

    private String headDetail;

    private String neckDetail;

    private String feetDetail;

    @ManyToOne
    private Kid kid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHeadDetail() {
        return headDetail;
    }

    public void setHeadDetail(String headDetail) {
        this.headDetail = headDetail;
    }

    public String getNeckDetail() {
        return neckDetail;
    }

    public void setNeckDetail(String neckDetail) {
        this.neckDetail = neckDetail;
    }

    public String getFeetDetail() {
        return feetDetail;
    }

    public void setFeetDetail(String feetDetail) {
        this.feetDetail = feetDetail;
    }

    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
    }
}
