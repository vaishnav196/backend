package com.HotelManagement.services;

import com.HotelManagement.Repository.ProductRepo;
import com.HotelManagement.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductService {

    @Autowired
    private  ProductRepo productRepo;



    @PostMapping("/product")
    public Product addproduct(@RequestBody Product pr){
        return  productRepo.save(pr);
    }


}
