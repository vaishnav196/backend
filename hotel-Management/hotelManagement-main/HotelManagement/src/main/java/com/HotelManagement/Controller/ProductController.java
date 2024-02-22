package com.HotelManagement.Controller;

import com.HotelManagement.Repository.ProductRepo;
import com.HotelManagement.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("http://localhost:4200")
@RestController
public class ProductController {

    @Autowired
    private ProductRepo productRepo;


    @PostMapping("/product")
    public Product addProduct(@RequestBody Product pr){
   productRepo.save(pr);
    return pr;
    }

    @GetMapping("/product")
    public List<Product> getAllProduct(){
    List<Product> allProduct=productRepo.findAll();
    return allProduct;
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable("productId") int productId){
    Product product=productRepo.findById(productId).orElse(new Product());
    return product;
    }

    @DeleteMapping("/product/{productId}")
    public Optional<Product> deleteProduct(@PathVariable("productId") int productId){
    Optional<Product> delete=productRepo.findById(productId);
    productRepo.deleteById(productId);
    return delete;
    }







}
