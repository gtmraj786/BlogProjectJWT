package com.example.springbootproject.pojo;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private String title;

    private String content;



    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private  Date published;
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;
    @UpdateTimestamp
    private Date updatedAt;
    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private Date createdAt;

    @ManyToMany(fetch =FetchType.LAZY,cascade ={CascadeType.PERSIST,CascadeType.MERGE})
    private List<Category> categories =new ArrayList<>() ;


    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }


    public void addCategory(Category categoryAdd)
    {

        if(categories==null)
        {
            categories=new ArrayList<>();

        }
        categories.add(categoryAdd);
    }


    @ManyToOne
    private Author author;


    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }



    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
