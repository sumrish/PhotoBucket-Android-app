package com.example.userphotobucket.ui.photo.model;

public class Photo {
    private float albumId;
    private float id;
    private String title;
    private String url;
    private String thumbnailUrl;


    // Getter Methods

    public float getAlbumId() {
        return albumId;
    }

    public float getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    // Setter Methods

    public void setAlbumId(float albumId) {
        this.albumId = albumId;
    }

    public void setId(float id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
