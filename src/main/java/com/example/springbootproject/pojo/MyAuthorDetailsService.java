package com.example.springbootproject.pojo;

import com.example.springbootproject.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyAuthorDetailsService implements UserDetailsService
{    @Autowired
    private AuthorRepository repo;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        Author author=repo.findAllByName(s);

        if(author==null)
            throw  new UsernameNotFoundException("Author 404");

        return new AuthorPrincipal(author.getName(),author.getPassword(),author.getRole());
    }
}
