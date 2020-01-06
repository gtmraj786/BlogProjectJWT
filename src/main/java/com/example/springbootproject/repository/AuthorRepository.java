package com.example.springbootproject.repository;

import com.example.springbootproject.pojo.Author;
import com.example.springbootproject.pojo.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer>, PagingAndSortingRepository<Author,Integer>
{
    //public void save();
    public Author findAllByAid(int aid);
    public Author findAllByName(String name);
    Author findByName(String name);
}
