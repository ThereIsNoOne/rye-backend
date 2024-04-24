package com.io.rye.rye.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class ParentTask {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private int length;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    private Kid kid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
    }
}
