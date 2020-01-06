package com.example.springbootproject.service;

import com.example.springbootproject.pojo.Author;
import com.example.springbootproject.pojo.Category;
import com.example.springbootproject.pojo.Post;
import com.example.springbootproject.repository.PostRepository;
import com.example.springbootproject.serviceInterface.PostServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
    @Service
    public class PostService implements PostServiceInterface
    {
        @Autowired
        private PostRepository repo;

        public List<Post> searchBy(String title,Pageable page)
        {
            return repo.findAllByTitleContainsOrContentContains(title,title,page);
        }


        public List<Post> listAll(Pageable pageable)
        {
            return repo.findAll(pageable).getContent();
        }

        //  public List<Posts> fetchByPage(Pageable page)
        //    {
        //        return repo.findAll(page).getContent();
        //    }
//        public List<Posts> sortByPid()
//        {
//            return repo.findAllByOrderByPidAsc();
//        }
        public List<Post> sortByPid(Pageable page)
        {
            return repo.findAllByOrderByPidDesc(page).getContent();
        }



//    public List<Post> sortByUpdateDate()
//    {
//        return repo.findAllByOrderByUpdatedAtDesc();
//    }
     public List<Post> sortByUpdateDate(Pageable page)
{
    return repo.findAllByOrderByUpdatedAtDesc(page).getContent();
}
    public List<Post> sortByCreatedDate(Pageable page)
    {
        return repo.findAllByOrderByCreatedAtDesc(page).getContent();
    }
    public List<Post> sortByTitle(Pageable page)
    {
        return repo.findAllByOrderByTitleAsc(page).getContent();
    }
        public List<Post> listAll() {
            return ((List<Post>) repo.findAll());
        }

        public void save(Post post) {
            repo.save(post);
        }

    public Post get(int id)
    {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public List<Post> getByCat(Category category,Pageable page)
    {
        return  repo.findAllByCategoriesContains(category,page).getContent();
    }


    public List<Post> getByAuthor(Author author, Pageable page)
     {
        return  repo.findAllByAuthor(author,page).getContent();
     }


}

