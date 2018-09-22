package com.redpepper.fooble;

public class Category {

    private int id;
    private String title;
    private String shortDescription;
    private String imageLink;
    private String counter;

    public Category(int id, String title, String shortDescription,String counter, String imageLink) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.counter = counter;
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

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
