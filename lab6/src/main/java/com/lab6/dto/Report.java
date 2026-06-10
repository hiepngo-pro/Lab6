package com.lab6.dto;

import java.io.Serializable;
import java.util.Date;

public class Report implements Serializable {
    private String group;   // video title
    private Long likes;
    private Date newest;
    private Date oldest;

    public Report(String group, Long likes, Date newest, Date oldest) {
        this.group = group;
        this.likes = likes;
        this.newest = newest;
        this.oldest = oldest;
    }

    // Getters / Setters
    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }
    public Long getLikes() { return likes; }
    public void setLikes(Long likes) { this.likes = likes; }
    public Date getNewest() { return newest; }
    public void setNewest(Date newest) { this.newest = newest; }
    public Date getOldest() { return oldest; }
    public void setOldest(Date oldest) { this.oldest = oldest; }
}