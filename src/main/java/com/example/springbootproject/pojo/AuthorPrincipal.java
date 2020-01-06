package com.example.springbootproject.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AuthorPrincipal implements UserDetails
{   private String name;
    private String password;
   private String role;

    // private Author author;
    private List<GrantedAuthority> authorities;

    public AuthorPrincipal(String name, String password,String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
//        if(role.equals("author"))
//        {
//            authorities= Arrays.asList(new SimpleGrantedAuthority("author"));
//        }
//        else
//        {
//            authorities=Arrays.asList(new SimpleGrantedAuthority("admin"));
//        }



//   // public AuthorPrincipal(Author author) {
//        this.author = author;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
        //return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
