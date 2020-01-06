package com.example.springbootproject.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;
   @Column(unique = true)
     private  String name;
    @CreationTimestamp
     @Column(nullable = false,updatable = false)
     private Date createdAt;
    @UpdateTimestamp
    @Column(nullable = false,updatable = false)
     private Date  updatedAt;


    @JsonIgnore
     @ManyToMany(mappedBy = "categories",fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
     private List<Post> posts=new ArrayList<>();

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public void addPost(Post postAdd)
    {
        if(posts==null)
        {
            posts=new ArrayList<>();
        }
            posts.add(postAdd);
    }
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
