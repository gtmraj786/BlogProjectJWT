package com.example.springbootproject.pojo;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aid;
     private String  name;
    private  String email;
   private String  password;
   private  String role;



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
   private Date createdAt;

    @UpdateTimestamp
   private Date  updatedAt;




   @OneToMany(mappedBy = "author",cascade=CascadeType.ALL)
   private List<Post> posts=new ArrayList<>();

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
