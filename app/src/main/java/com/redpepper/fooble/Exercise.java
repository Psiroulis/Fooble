package com.redpepper.fooble;

public class Exercise {

    private int id;
    private String name;
    private int level;
    private String levelText;
    private String description;
    private String bibliography;
    private String videoPath;

    public Exercise(int id, String name, int level, String description, String bibliography, String videoPath) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.description = description;
        this.bibliography = bibliography;
        this.videoPath = videoPath;
        if(level == 1){
            levelText = "Easy";
        }else if(level == 2){
            levelText = "Medium";
        }else if(level == 3){
            levelText = "Hard";
        }
    }

    public Exercise(int id, String name, int level) {
        this.id = id;
        this.name = name;
        this.level = level;
        if(level == 1){
            levelText = "Easy";
        }else if(level == 2){
            levelText = "Medium";
        }else if(level == 3){
            levelText = "Hard";
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
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

    public void setLevel(int level) {
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
}
