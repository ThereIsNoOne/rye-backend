package com.io.rye.rye.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Result {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column(nullable = false)
    private String mode;

    @Column(nullable = false)
    private int time;

    @Column(nullable = false)
    private int result;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private int reward;

    @ManyToOne
    private Kid kid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
    }
}
