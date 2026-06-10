package com.lab6.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "Id", length = 20)
    private String id;

    @Column(name = "Password", length = 50, nullable = false)
    private String password;

    @Column(name = "Fullname", length = 50, nullable = false)
    private String fullname;

    @Column(name = "Email", length = 50, nullable = false)
    private String email;

    @Column(name = "Admin", nullable = false)
    private boolean admin;

    @Column(name = "Phone", length = 15)
    private String phone;

    // Quan hệ One-to-Many với Favorite
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Favorite> favorites;

    // Constructor mặc định (bắt buộc với JPA)
    public User() {}

    // Constructor đầy đủ (dùng để tạo mới)
    public User(String id, String password, String fullname, String email, boolean admin, String phone) {
        this.id = id;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.admin = admin;
        this.phone = phone;
    }

    // Getters và Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isAdmin() { return admin; }
    public void setAdmin(boolean admin) { this.admin = admin; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public List<Favorite> getFavorites() { return favorites; }
    public void setFavorites(List<Favorite> favorites) { this.favorites = favorites; }

    @Override
    public String toString() {
        return "User{id='" + id + "', fullname='" + fullname + "', email='" + email + "', admin=" + admin + ", phone='" + phone + "'}";
    }
}