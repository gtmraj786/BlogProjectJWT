//package com.example.springbootproject.controller;
//
//
import com.example.springbootproject.pojo.Author;
import com.example.springbootproject.repository.AuthorRepository;
import com.example.springbootproject.service.AuthorService;
import com.example.springbootproject.service.CategoryService;
import com.example.springbootproject.service.PostService;
import com.example.springbootproject.pojo.Category;
import com.example.springbootproject.pojo.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.*;
//
//@Controller
//public class PostController {
//
//    private static final Logger LOGGER= LoggerFactory.getLogger(PostController.class);
//    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
//     @Autowired
//    private AuthorRepository authorRepository;
//
//    @Autowired
//    private PostService service;
//   @Autowired
//    private CategoryService serviceCat;
//   @Autowired
//   private AuthorService serviceAuthor;
//
////    @RequestMapping("/login")
////    public String loginPage()
////    {
////        System.out.println("jhjkhk");
////        return "login";
////    }
//    @RequestMapping("/login")
//    public ModelAndView loginPage()
//    {  ModelAndView mv=new ModelAndView("login");
//
//        mv.addObject("author",new Author());
//        return mv;
//    }
//
//    @RequestMapping("/logout-success")
//    public String logoutPage()
//    {
//        return "logout";
//    }
//
//    @RequestMapping("/")
//    public String first()
//    {
//        return "redirect:/home/0";
//    }
//    @RequestMapping("home/{pageNo}")
//    public ModelAndView home(@PathVariable("pageNo")int pag)
//    {   LOGGER.info("Home page upload...");
//        Pageable page=PageRequest.of(pag,3);
//         ModelAndView mvError=new ModelAndView("error-page");
//        ModelAndView mv = new ModelAndView("home");
//        List<Category> categoryList;
//        try {
//             categoryList = serviceCat.listAll();
//
//        } catch (Exception e)
//        {  LOGGER.warn("Exception come during fetching list of category");
//            return mvError;
//        }
//        mv.addObject("listCategory",categoryList);
//        List<Post> listPost;
//        try {
//             listPost = service.sortByPid(page);
//        }catch (Exception e)
//        {   LOGGER.warn("Exception during fetching");
//            return mvError;
//        }
//        mv.addObject("listPost", listPost);
//        mv.addObject("lastPage",(service.sortByPid(page).size())/3);
//        mv.addObject("pageNo",pag);
//
//        return mv;
//    }
//
//    @RequestMapping("/sortByUpdateDate/{pageNo}")
//    public ModelAndView sortByUpdate(@PathVariable("pageNo")int pag)
//    {    Pageable page=PageRequest.of(pag,3);
//        ModelAndView mvError=new ModelAndView("error-page");
//        ModelAndView mv = new ModelAndView("home");
//        LOGGER.info("Sort the according to updated date");
//        List<Post> listPost;
//        try {
//            listPost = service.sortByUpdateDate(page);
//        }catch (Exception e)
//        {
//            return mvError;
//        }
//        mv.addObject("listPost", listPost);
//        mv.addObject("lastPage",(service.sortByPid(page).size())/3);
//        mv.addObject("pageNo",pag);
//
//
//        return mv;
//    }
//    @RequestMapping("/sortByCreate/{pageNo}")
//    public ModelAndView sortByCreateDate(@PathVariable("pageNo")int pag)
//    {   Pageable page=PageRequest.of(pag,3);
//        ModelAndView mvError=new ModelAndView("error-page");
//        ModelAndView mv = new ModelAndView("home");
//        LOGGER.info("Sort the according to craeted date");
//        List<Post> listPost;
//        try {
//            listPost = service.sortByCreatedDate(page);
//        }catch (Exception e)
//        {
//            return mvError;
//        }
//        mv.addObject("listPost", listPost);
//        mv.addObject("lastPage",(service.sortByPid(page).size())/3);
//        mv.addObject("pageNo",pag);
//
//        return mv;
//    }
//    @RequestMapping("searchBy/{pageNo}")
//    public ModelAndView filterByCategory(HttpServletRequest req,@PathVariable("pageNo")int pag)
//    {
//        ModelAndView mvError=new ModelAndView("error-page");
//        Pageable page=PageRequest.of(pag,3);
//        String search=req.getParameter("search");
//        ModelAndView mv = new ModelAndView("home");
//        LOGGER.info("Searching...");
//        List<Post> listPost;
//        try {
//            listPost = service.searchBy(search, page);
//        }catch (Exception e)
//        {  LOGGER.error("Exception during searching");
//            return mvError;
//        }
//        mv.addObject("listPost", listPost);
//        try {
//            mv.addObject("lastPage", (service.sortByPid(page).size()) / 3);
//        }catch (Exception e)
//        {
//            return mvError;
//        }
//        mv.addObject("pageNo",pag);
//
//        return mv;
//    }
//
//    @RequestMapping("/sortByTitle/{pageNo}")
//    public ModelAndView sortByTitle(@PathVariable("pageNo")int pag)
//    {
//        Pageable page=PageRequest.of(pag,3);
//    ModelAndView mvError=new ModelAndView("error-page");
//        ModelAndView mv = new ModelAndView("home");
//        LOGGER.info("Sort by title");
//        List<Post> listPost;
//        try {
//            listPost = service.sortByTitle(page);
//        }catch (Exception e)
//        {
//            return mvError;
//        }
//        mv.addObject("listPost", listPost);
//        mv.addObject("lastPage",(service.sortByPid(page).size())/3);
//        mv.addObject("pageNo",pag);
//
//        return mv;
//    }
//
//    @RequestMapping("filterBy/{cat}/{pageNo}")
//    public  ModelAndView filterByCategory(@PathVariable("cat")String cat,@PathVariable("pageNo")int pag)
//    {     ModelAndView mvError=new ModelAndView("error-page");
//        LOGGER.info("Find by category");
//        System.out.println(cat);
//         Pageable page=PageRequest.of(pag,3);
//        Category category;
//        try {
//             category = serviceCat.findCat(cat);
//            System.out.println("mmm");
//        }catch (Exception e)
//        {   LOGGER.error("Exception during finding category");
//            return mvError;
//        }
//        ModelAndView mv=new ModelAndView("home");
//        List<Post> listPost;
//        try {
//             listPost = service.getByCat(category, page);
//        }catch (Exception e)
//        {    LOGGER.error("Exception during finding post by category");
//            return mvError;
//        }
//        mv.addObject("listPost", listPost);
//        try {
//            mv.addObject("lastPage", (service.sortByPid(page).size()) / 3);
//        }catch (Exception e)
//        {
//            return mvError;
//        }
//        mv.addObject("pageNo",pag);
//
//        return  mv;
//    }
//    @RequestMapping("filterByAuthor/{author}/{pageNo}")
//    public  ModelAndView filterByAuthor(@PathVariable("author")String author1,@PathVariable("pageNo")int pag)
//    {  ModelAndView mvError=new ModelAndView("error-page");
//        Pageable page=PageRequest.of(pag,3);
//        Author author;
//        LOGGER.info("Find by Author");
//        try {
//             author = serviceAuthor.findAuthor(author1);
//        }
//        catch (Exception e)
//        {   LOGGER.error("Exception during finding Author by Author name");
//         return mvError;
//        }
//        ModelAndView mv=new ModelAndView("home");
//        List<Post> listPost;
//        try {
//             listPost = service.getByAuthor(author, page);
//        }catch (Exception e)
//        {  LOGGER.error("Exception during finding post by author");
//            return mvError;
//        }
//        mv.addObject("listPost", listPost);
//        mv.addObject("lastPage",(service.sortByPid(page).size())/3);
//        mv.addObject("pageNo",pag);
//
//        return  mv;
//    }
//
//    @RequestMapping("/addPost")
//    public String addpost(Model model)
//    {
//        LOGGER.info("Post created");
//        Map<Category,String> mp=new HashMap<>();
//        Post p=new Post();
//       try {
//           serviceCat.listAll().forEach((category -> {
//               mp.put(category, category.getName());
//           }));
//       }catch (Exception e)
//       {
//           return "error-page";
//       }
//        model.addAttribute("post",p);
//        model.addAttribute("mapOfCategory",mp);
//
//        return "post-form";
//    }
//
//    @RequestMapping("save")
//    public String save(@ModelAttribute("post") Post post)
//    {    LOGGER.info("Store the post into database");
//         Author author;
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String name = authentication.getName();
//         try {
//              author = authorRepository.findByName(name);
//         }catch (Exception e)
//         {   LOGGER.error("Exception during finding Author by author name");
//             return "error-page";
//         }
//        post.setAuthorId(author.getAid());
//        post.setAuthor(author);
//        try {
//            service.save(post);
//        }catch (Exception e)
//        {   LOGGER.error("Exception during saving the post");
//            return "error-page";}
//     return "redirect:/home/0";
//    }
//
//    @RequestMapping("addCategory")
//    public String addCategory(Model model) {
//        LOGGER.info("Add Category");
//        Category cat = new Category();
//         Author author;
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String name = authentication.getName();
//        try {
//             author = authorRepository.findByName(name);
//        }catch (Exception e)
//        { LOGGER.error("Exception during finding Author by author name");
//            return  "error-page";
//        }
//
//        System.out.println(author.getRole());
//        if (author.getRole().equals("admin")) {
//            model.addAttribute("category", cat);
//            return "insert-category-form";
//        } else return "invalid-author";
//    }
//
//
//    @PostMapping("saveCategory")
//    public String saveCategory(@ModelAttribute("category") Category category)
//    {
//      LOGGER.info("Store  the category into database");
//        try {
//            serviceCat.save(category);
//
//        }catch (Exception e)
//        {
//
//            return "error-page";
//
//        }
//        return "redirect:/home/0";
//
//    }
//
//
//
//    @RequestMapping(value = "delete")
//    public String deleteCustomer(@RequestParam int pid) {
//           LOGGER.info("Delete the post by post Id");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String name = authentication.getName();
//        Author author;
//        Post post;
//        int aid;
//        try {
//             author = authorRepository.findByName(name);
//             aid = author.getAid();
//             post = service.get(pid);
//        }catch (Exception e)
//        {
//            return "error-page";
//        }
////        System.out.println(post.getAuthorId());
////       System.out.println(aid);
//       if(author.getRole().equals("admin"))
//       {
//           try {
//               service.delete(pid);
//           }catch (Exception e)
//           {
//               return "error-page";
//           }
//           return "redirect:/home/0";
//       }
//       else if(post.getAuthorId()==aid)
//       {   try {
//               service.delete(pid);
//               }catch (Exception e)
//                 {
//                      return "error-page";
//                  }
//            return "redirect:/home/0";
//        }else
//            return  "invalid-author";
//    }
//
//    @RequestMapping("deleteConfirm")
//    public ModelAndView deleteConfirm(@RequestParam int id) {
//        LOGGER.info("Post delete confirmation");
//       Post post;
//        ModelAndView mvError=new ModelAndView("error-page");
//        ModelAndView mv = new ModelAndView("delete-confirm");
//        try {
//             post = service.get(id);
//        }catch (Exception e)
//        {
//            return mvError;
//        }
//        mv.addObject("post", post);
//        return mv;
//
//
//    }
//
//    @RequestMapping("edit")
//    public String editPostForm(@RequestParam int id,Model model)
//    {  LOGGER.info("Edit the post");
//        Author author;
//        Post post;
//        int aid;
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String name = authentication.getName();
//       try {
//            author = authorRepository.findByName(name);
//               aid = author.getAid();
//            post = service.get(id);
//       }catch (Exception e)
//       { return "error-page";}
//
//        if(author.getRole().equals("admin"))
//        {
//            Map<Category, String> mp = new HashMap<>();
//
//           try {
//               serviceCat.listAll().forEach((category -> {
//                   mp.put(category, category.getName());
//               }));
//           }catch (Exception e)
//           {
//               return "error-page";
//           }
//            model.addAttribute("post", post);
//            model.addAttribute("mapOfCategory", mp);
//            return "edit-post";
//
//        }
//        else
//            if(post.getAuthorId()==aid) {
//            Map<Category, String> mp = new HashMap<>();
//            System.out.println(post.getAuthorId());
//            try {
//                serviceCat.listAll().forEach((category -> {
//                    mp.put(category, category.getName());
//                }));
//            }
//            catch (Exception e)
//            {
//                return "error-page";
//            }
//            model.addAttribute("post", post);
//            model.addAttribute("mapOfCategory", mp);
//            return "edit-post";
//        }
//        else
//            return "invalid-author";
//    }
//
//
//
//    @RequestMapping("addAuthor")
//    public String addAuthor(Model model)
//    {
//         LOGGER.info("Add Author");
//        Author author=new Author();
//
//        model.addAttribute("author",author);
//        return "add-author-form";
//    }
//
//
//    @RequestMapping("saveAuthor")
//    public String saveAuthor(@ModelAttribute("author") Author author)
//    {   LOGGER.info("Save Author detail into database");
//        String password1=bCryptPasswordEncoder.encode(author.getPassword());
//
//       // System.out.println(author.getName());
//
//        if(author.getName().equals("Admin"))
//        {
//            author.setRole("admin");
//        }
//        else {
//            author.setRole("author");
//        }
//        author.setPassword(password1);
//        try {
//            serviceAuthor.save(author);
//        }catch (Exception e)
//        {
//            return "error-page";
//        }
//        return "redirect:/home/0";
//    }
//
//
//    @GetMapping("posts")
//    public ModelAndView filteringSortingSearching(@RequestParam(required = false,defaultValue = "",name ="category")String cat,
//                                                  @RequestParam(required = false,defaultValue = "",name ="search")String search,
//                                                  @RequestParam(required = false,defaultValue = "0",name = "page")int page,
//                                                  @RequestParam(required = false,defaultValue = "3",name = "size")int size)
//    {
//
//
//        Pageable pageable=PageRequest.of(page,size);
//
//        Category category=serviceCat.findCat(cat);
//
//        ModelAndView mv=new ModelAndView("home");
//        List<Post> listPost =service.getByCat(category,pageable);
//        mv.addObject("listPost", listPost);
//        mv.addObject("lastPage",(service.sortByPid(pageable).size())/size);
//        mv.addObject("pageNo",page);
//
//        return  mv;
//
//    }
//
//
//}
