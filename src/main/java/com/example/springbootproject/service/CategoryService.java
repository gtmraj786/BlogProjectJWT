package com.example.springbootproject.service;


import com.example.springbootproject.pojo.Category;
import com.example.springbootproject.repository.CategoryRepository;
import com.example.springbootproject.serviceInterface.CategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoryServiceInterface
{    @Autowired
    private CategoryRepository repo;

    public List<Category> listAll() {
        return ((List<Category>) repo.findAll());
    }


        public void save(Category category) {
            repo.save(category);
        }

            public Category findCat(String cat)
            {
                return repo.findByName(cat);
            }


}
