package com.io.rye.rye.dto;

import java.sql.Date;

public class ParentTaskDto {
    private int id;
    private String type;
    private int length;
    private int duration;
    private Date date;
    private KidDto kid;

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

    public KidDto getKid() {
        return kid;
    }

    public void setKid(KidDto kid) {
        this.kid = kid;
    }
}
