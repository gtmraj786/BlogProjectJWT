package com.example.springbootproject.serviceInterface;

import com.example.springbootproject.pojo.Category;

import java.util.List;

public interface CategoryServiceInterface {
    public List<Category> listAll();
    public void save(Category category);
    public Category findCat(String cat);
}
