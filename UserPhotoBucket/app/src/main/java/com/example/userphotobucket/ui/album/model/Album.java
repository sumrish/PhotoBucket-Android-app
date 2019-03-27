package com.example.userphotobucket.ui.album.model;

public class Album {
    private float userId;
    private float id;
    private String title;
    private String thumbnailUrl;

    public String getThumbnail() {
        return thumbnailUrl;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnailUrl = thumbnail;
    }

    // Getter Methods

    public float getUserId() {
        return userId;
    }

    public float getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    // Setter Methods

    public void setUserId(float userId) {
        this.userId = userId;
    }

    public void setId(float id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
