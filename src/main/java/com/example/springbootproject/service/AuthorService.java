package com.example.springbootproject.service;


import com.example.springbootproject.pojo.Author;
import com.example.springbootproject.repository.AuthorRepository;
import com.example.springbootproject.serviceInterface.AuthorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService implements AuthorServiceInterface
{   @Autowired
    private AuthorRepository repo;

   public void save(Author author)
   {
       repo.save(author);
   }

    public Author findAuthor(String author)
    {
        return repo.findByName(author);
    }

}
