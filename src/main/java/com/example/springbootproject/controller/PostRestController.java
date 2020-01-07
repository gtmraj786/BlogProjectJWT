package com.example.springbootproject.controller;


import com.example.springbootproject.pojo.*;
import com.example.springbootproject.repository.PostRepository;
import com.example.springbootproject.service.AuthorService;
import com.example.springbootproject.service.CategoryService;
import com.example.springbootproject.service.PostService;
import com.example.springbootproject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//Gautam
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostRestController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyAuthorDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;


    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationtoken(@RequestBody AuthenticateRequest authenticateRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(), authenticateRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticateRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticateResponse(jwt));
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable int id) {
        return postService.get(id);
    }

    @GetMapping("/")
    public List<Post> getPosts() {

        Pageable page = PageRequest.of(0, 10);
        return postService.sortByPid(page);
    }


    @DeleteMapping("/delete")
    public String deletePost(@RequestParam int id,
                             Principal principal, SecurityContextHolderAwareRequestWrapper requestWrapper)
    {    //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         //authentication.getName();
        String name = principal.getName();


        Post currentPost;
        try {
            currentPost= postService.get(id);

        } catch (Exception e) {
            return "error";

        }

        String postAuthorName = currentPost.getAuthor().getName();

        if (postAuthorName.equals(name) || requestWrapper.isUserInRole("Admin")) {
            postService.delete(id);
            return "deleted successfully";
        } else {
            return "Invalid Author";
        }
    }

    @PostMapping("/create-post")
    public String createPost(
            @RequestParam(required = true, defaultValue = "", name = "title") String title,
            @RequestParam(required = true, defaultValue = "", name = "content") String content,
            @RequestParam(required = false, defaultValue = "other", name = "category") String category
    ) {
        Post post = new Post();
        post.setTitle(title);

        Pageable page = PageRequest.of(0, 3);

        String arr[] = category.split(",");

        List<Category> categories = new ArrayList<>();
        for (String s : arr) {
            System.out.println(categoryService.findCat(s).getName());
            categories.add(categoryService.findCat(s));

        }
        post.setCategories(categories);
        post.setContent(content);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

         Author currentAuthor = authorService.findAuthor(name);

      post.setAuthorId(currentAuthor.getAid());
        post.setAuthor(currentAuthor);
        postService.save(post);
        return "Post created successfully";
    }




    @PostMapping("/addCategory")
    public String saveCategory( @RequestParam(required = true, defaultValue = "", name = "category") String cat)
    {
        Category category=new Category();

        category.setName(cat);
        categoryService.save(category);


        return "Category Added successfully";

    }

    @PutMapping("/update")
    public String update(@RequestParam int id,
                       @RequestParam(required = true, defaultValue = "", name = "title") String title,
                       @RequestParam(required = false, defaultValue = "", name = "content") String content,
                       @RequestParam(required = false, defaultValue = "other", name = "category") String category,
                       Principal principal, SecurityContextHolderAwareRequestWrapper requestWrapper) {
        Post post = postService.get(id);
        System.out.println(post.getPid());
        post.setTitle(title);
        post.setContent(content);
        //Pageable page= PageRequest.of(0,3);


        String arr[] = category.split(",");


        List<Category> categories = new ArrayList<>();
        for (String s : arr) {
            categories.add(categoryService.findCat(s));

        }

        String name = principal.getName();

        Post currentPost = postService.get(id);

        String postAuthorName = currentPost.getAuthor().getName();

        if (postAuthorName.equals(name) || requestWrapper.isUserInRole("Admin")) {

            postService.save(post);
            return "post updated successfully";

        } else {
            return "Invalid author";
        }

    }

    @PostMapping("/signUp")
    public String saveAuthor(@RequestParam("name") String name,
                            @RequestParam("password") String password,
                             @RequestParam("email")String email) {
        Author author = new Author();
        System.out.println("KLKLKL");
        String encryptedPass = passwordEncoder.encode(password);
        author.setPassword(encryptedPass);
        author.setName(name);
        author.setEmail(email);

        if(author.getName().equals("Admin"))
            author.setRole("admin");
        else
        author.setRole("author");
        try {
            authorService.save(author);
        }catch (Exception e) {
            return "error";
        }
        return "Account created  sucessfully";
    }
    @GetMapping("/posts")
    public List<Post> searchingSortingFiltering(@RequestParam(required = false, name = "search") String search,
                                                @RequestParam(required = false, name = "category") String category,
                                                @RequestParam(defaultValue = "pid", required = false, name = "sortBy") String sortBy,
                                                @RequestParam(defaultValue = "0", required = false, name = "page") Integer page,
                                                @RequestParam(defaultValue = "3", required = false, name = "size") Integer size) {

        if (search != null) {

            if (category != null) {

                Category cat = categoryService.findCat(category);


                List<Post> listByCategory = cat.getPosts();


                Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());


                List<Post> listBySearch = postService.searchBy(search, pageable);


                List<Post> listByCategoryAndSearch = new ArrayList<>();

                listBySearch.forEach((i) -> {
                    if (listByCategory.contains(i)) {
                        listByCategoryAndSearch.add(i);
                    }
                });

                return listByCategoryAndSearch;
            } else {
                Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
                List<Post> listOfPosts = postService.searchBy(search, pageable);
                return listOfPosts;
            }
        } else {
            if (category != null) {

                Category cat = categoryService.findCat(category);


                List<Post> listByCategory = cat.getPosts();


                Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());


                List<Post> postsList = postService.listAll(pageable);

                List<Post> finalList = new ArrayList<>();

                postsList.forEach((i) -> {
                    if (listByCategory.contains(i)) {
                        finalList.add(i);
                    }
                });
                return finalList;
            } else {
                Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
                return postService.listAll(pageable);
            }

        }
    }




}






