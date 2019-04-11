package com.geekbrains.springbootproject.controllers;

import com.geekbrains.springbootproject.entities.Comment;
import com.geekbrains.springbootproject.entities.Order;
import com.geekbrains.springbootproject.entities.Product;
import com.geekbrains.springbootproject.entities.User;

import com.geekbrains.springbootproject.services.CommentService;
import com.geekbrains.springbootproject.services.OrderService;
import com.geekbrains.springbootproject.services.ProductsService;
import com.geekbrains.springbootproject.services.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    private ProductsService productsService;
    private OrderService orderService;
    private UserService userService;
    private CommentService commentService;

    public MainController(ProductsService productsService,
                          OrderService orderService,
                          UserService userService,
                          CommentService commentService) {
        this.productsService = productsService;
        this.orderService = orderService;
        this.userService = userService;
        this.commentService = commentService;
    }


/*    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }*/

    @GetMapping("/info")
    public String showInfoPage(Model model) {
        return "info";
    }

    @GetMapping("/profile")
    public String showUserProfile(Model model, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        List<Order> orderList = orderService.getAllOrdersByUser(user);
        model.addAttribute("orderList", orderList);
        return "profile";
    }

    @GetMapping("/comment/{productId}")
    public String createComment(Model model, @PathVariable("productId") Long productId) {
        Comment comment = new Comment();
        Product product = productsService.findById(productId);
        comment.setProduct(product);
        model.addAttribute("comment", comment);
        return "create-comment-page";
    }

    @PostMapping("/createcomment")
    public String saveComment(Model model, Principal principal, @ModelAttribute("comment") Comment comment) {
        User user = userService.findByUserName(principal.getName());
        comment.setUser(user);
        commentService.saveComment(comment);
        return "redirect:/shop";
    }


    @GetMapping("/product/edit/{id}")
    public String addProductPage(Model model, @PathVariable("id") Long id) {
        Product product = productsService.findById(id);
        if (product == null) {
            product = new Product();
        }
        model.addAttribute("product", product);
        return "edit-product";
    }

    // Binding Result после @ValidModel !!!
    @PostMapping("/product/edit")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit-product";
        }

        Product existing = productsService.findByTitle(product.getTitle());
        if (existing != null && (product.getId() == null || !product.getId().equals(existing.getId()))) {
            // product.setTitle(null);
            model.addAttribute("product", product);
            model.addAttribute("productCreationError", "Product title already exists");
            return "edit-product";
        }
        productsService.saveOrUpdate(product);
        return "redirect:/";
    }
}
