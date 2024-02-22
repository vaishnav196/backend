package com.HotelManagement.Controller;

import com.HotelManagement.Repository.CartProductRepo;
import com.HotelManagement.Repository.CartRepo;
import com.HotelManagement.Repository.ProductRepo;
import com.HotelManagement.Repository.UserRepo;
import com.HotelManagement.model.Cart;
import com.HotelManagement.model.CartProduct;
import com.HotelManagement.model.Product;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
public class CartController {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CartProductRepo cartProductRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private  CartRepo cartRepo;

    @GetMapping("/cart")
    public List<Cart> getCartProduct(){
        List<Cart> allProduct=cartRepo.findAll();
        return allProduct;
    }

    @GetMapping("/cart/{cartId}")
    public Cart getCartProduct(@PathVariable("cartId") int cartId,Product product){
        Cart Cartproduct=cartRepo.findById(cartId).orElse(null);
        Cartproduct.getProduct();
        cartRepo.save(Cartproduct);
        return Cartproduct;
    }


    @PostMapping("/{cartId}/product/{productId}")
    public String addToCart(@PathVariable("cartId") int cartId, @PathVariable("productId") int productId){
        double totalPrice=0;
        Cart cart= cartRepo.findById(cartId).orElse(null);
        if(cart==null){
            return "Nothing is there in Cart";
        }

        Product product=productRepo.findById(productId).orElse(null);
        if(product==null){
            return "Product not Found";
        }
//        cart.getProduct().add(product);
//        cartRepo.save(cart);
//
        for(Product pr:cart.getProduct()){
            totalPrice= totalPrice +(pr.getPrice());
//            quantity++;

        }

        CartProduct cp = new CartProduct();
        cp.setProductId(productId);
        cp.setCartId(cartId);
        cp.setProductQuantity(1);

        cartProductRepo.save(cp);
        cart.setTotalPrice(totalPrice);
        cartRepo.save(cart);
        return "Successfully added to cart ";
    }

//    @PostMapping("/{cartId}/product/{productId}")
//    public String addToCart(@PathVariable("cartId") int cartId, @PathVariable("productId") int productId){
//        double totalPrice=0;
//        int quantity=0;
////        double discount=0;
//        Cart cart= cartRepo.findById(cartId).orElse(null);
//        if(cart==null){
//            return "Nothing is there in Cart";
//        }
//
//        Product product=productRepo.findById(productId).orElse(null);
//        if(product==null){
//            return "Product not Found";
//        }
//        cart.getProduct().add(product);
//        cartRepo.save(cart);
//        for(Product pr:cart.getProduct()){
//            totalPrice= totalPrice +(pr.getPrice());
//            quantity++;
//        }
//
//        cart.setTotalPrice(totalPrice);
//        cart.setQuantity(quantity);
//        cartRepo.save(cart);
//
//        return "Successfully added to cart ";
//
//    }



    @DeleteMapping("{cartId}/product/{productId}")
    public String  deleteFromCart(@PathVariable("productId") int productId,@PathVariable("cartId") int cartId){
        double totalPriceNew = 0;
        Cart cart =cartRepo.findById(cartId).orElse(null);
        Product product=productRepo.findById(productId).orElse(null);
      CartProduct cp=new CartProduct();

        cart.getProduct().remove(product);
        double totalPricenew=cart.getTotalPrice();

//        cart.setQuantity(cart.getQuantity() - 1);
        cart.setTotalPrice(cart.getTotalPrice() - product.getPrice());


//        if (cart.getQuantity() < 0) {
//            cart.setQuantity(0);
//        }

        if (cart.getTotalPrice() < 0) {
            cart.setTotalPrice(0);
        }
        cartRepo.save(cart);

        return "product Removed From Cart";
    }



}