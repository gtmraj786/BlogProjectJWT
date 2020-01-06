package com.example.springbootproject.repository;

import com.example.springbootproject.pojo.Author;
import com.example.springbootproject.pojo.Category;
import com.example.springbootproject.pojo.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer>,PagingAndSortingRepository<Post,Integer>
{
    public Page<Post> findAll(Pageable pageable);
         //  public Page<Posts> findAllByOrderByPidDesc
//   public List<Posts> findAllByOrderByPidAsc();
   public Page<Post> findAllByOrderByPidDesc(Pageable pageable);
   // public  List<Post> findAllByOrderByUpdatedAtDesc();

    public  Page<Post> findAllByOrderByUpdatedAtDesc(Pageable pageable);
    public  Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
    public Page<Post> findAllByOrderByTitleAsc(Pageable pageable);
    public List<Post> findAllByTitleContainsOrContentContains(String title, String content,Pageable pageable);
    public  Page<Post> findAllByCategoriesContains(Category category,Pageable pageable);
    public Page<Post> findAllByAuthor(Author author,Pageable pageable);
 // public Page<Post> findAll(Pageable pageable);
}
