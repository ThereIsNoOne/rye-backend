package com.io.rye.rye.entity;

import jakarta.persistence.*;

@Entity
public class Picture {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column(unique = true, nullable = false)
    private String url;

    @Column(nullable = false)
    private String emotion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }
}
