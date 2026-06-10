package com.lab6.entity;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
    @NamedQuery(name = "Video.findByKeyword",
                query = "SELECT DISTINCT o.video FROM Favorite o WHERE o.video.title LIKE :keyword"),
    @NamedQuery(name = "Video.findByUser",
                query = "SELECT o.video FROM Favorite o WHERE o.user.id = :userId"),
    @NamedQuery(name = "Video.findInRange",
                query = "SELECT DISTINCT o.video FROM Favorite o WHERE o.likeDate BETWEEN :minDate AND :maxDate"),
    @NamedQuery(name = "Video.findInMonths",
                query = "SELECT DISTINCT o.video FROM Favorite o WHERE MONTH(o.likeDate) IN (:months)")
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Video.random10",
                      query = "SELECT TOP 10 * FROM Videos ORDER BY NEWID()",
                      resultClass = Video.class)
})
@Entity
@Table(name = "Videos")
public class Video {
    @Id
    @Column(name = "Id", length = 20)
    private String id;

    @Column(name = "Title", length = 100, nullable = false)
    private String title;

    @Column(name = "Poster", length = 200)
    private String poster;

    @Column(name = "Description", length = 500)
    private String description;

    @Column(name = "Views")
    private Integer views = 0;

    @Column(name = "Active")
    private Boolean active = true;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Favorite> favorites;

    public Video() {}
    public Video(String id, String title, String poster, String description, Integer views, Boolean active) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.description = description;
        this.views = views;
        this.active = active;
    }

    // Getters / Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getViews() { return views; }
    public void setViews(Integer views) { this.views = views; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public List<Favorite> getFavorites() { return favorites; }
    public void setFavorites(List<Favorite> favorites) { this.favorites = favorites; }
}