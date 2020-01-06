package com.example.springbootproject.repository;

import com.example.springbootproject.pojo.Category;
import com.example.springbootproject.pojo.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category,Integer>
{
    //List<Posts> findAllBy
    public Category findByName(String cat);



}
