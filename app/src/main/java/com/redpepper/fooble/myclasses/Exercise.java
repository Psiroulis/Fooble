package com.redpepper.fooble.myclasses;

public class Exercise {

    private int id;
    private String name;
    private String level;
    private String levelText;
    private String description;
    private String bibliography;
    private String videoPath;
    private boolean isDone;

    public Exercise(int id, String name, String level, String description, String bibliography, String videoPath) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.description = description;
        this.bibliography = bibliography;
        this.videoPath = videoPath;
        if(level.equalsIgnoreCase("beg")){
            levelText = "Beginner";
        }else if(level.equalsIgnoreCase("exp")){
            levelText = "Expert";
        }else if(level.equalsIgnoreCase("elt")){
            levelText = "Elite";
        }
    }

    public Exercise(int id, String name, String level, boolean isDone) {
        this.id = id;
        this.name = name;
        this.level = level;
        if(level.equalsIgnoreCase("beg")){
            levelText = "Beginner";
        }else if(level.equalsIgnoreCase("exp")){
            levelText = "Expert";
        }else if(level.equalsIgnoreCase("elt")){
            levelText = "Elite";
        }
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public String getBibliography() {
        return bibliography;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBibliography(String bibliography) {
        this.bibliography = bibliography;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getLevelText() {
        return levelText;
    }

    public void setLevelText(String levelText) {
        this.levelText = levelText;
    }

    public boolean getIsDone() { return isDone; }

    public void setDone(boolean done) { isDone = done; }
}
