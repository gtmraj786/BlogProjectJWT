package com.example.springbootproject.serviceInterface;

import com.example.springbootproject.pojo.Author;
import com.example.springbootproject.pojo.Category;
import com.example.springbootproject.pojo.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostServiceInterface {
    public List<Post> searchBy(String title, Pageable page);
    public List<Post> sortByPid(Pageable page);
    public List<Post> sortByUpdateDate(Pageable page);
    public List<Post> sortByCreatedDate(Pageable page);
    public List<Post> sortByTitle(Pageable page);
    public List<Post> listAll();
    public void save(Post post);
    public Post get(int id);
    public void delete(int id);
    public List<Post> getByCat(Category category, Pageable page);
    public List<Post> getByAuthor(Author author, Pageable page);
}
