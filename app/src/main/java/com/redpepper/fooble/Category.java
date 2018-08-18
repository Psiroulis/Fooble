package com.redpepper.fooble;

public class Category {

    private int id;
    private String title;
    private String shortDescription;
    private String longDescription;
    private String imageLink;

    public Category(int id, String title, String shortDescription, String imageLink) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.imageLink = imageLink;
        this.longDescription = null;
    }

    public Category(int id, String title, String shortDescription, String longDescription, String imageLink) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.imageLink = imageLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
