package com.module.mynotebook;

import android.net.Uri;

public class Note {
    String title;
    String description;
    String createTime;
    String imageuri;

    public Note(String title, String description, String createTime, String imageuri) {
        this.title = title;
        this.description = description;
        this.createTime = createTime;
        this.imageuri=imageuri;
    }
    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
