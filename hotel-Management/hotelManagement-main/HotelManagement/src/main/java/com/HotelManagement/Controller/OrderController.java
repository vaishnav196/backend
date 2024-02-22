package com.HotelManagement.Controller;

import com.HotelManagement.Repository.CartRepo;
import com.HotelManagement.Repository.OrderRepo;
import com.HotelManagement.Repository.ProductRepo;
import com.HotelManagement.Repository.UserRepo;
import com.HotelManagement.model.Cart;
import com.HotelManagement.model.Orders;
import com.HotelManagement.model.Product;
import com.HotelManagement.model.User;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin("http://localhost:4200")
@RestController
public class OrderController {

@Autowired
private CartRepo cartRepo;


@Autowired
private OrderRepo orderRepo;

@Autowired
private UserRepo userRepo;

@Autowired
private ProductRepo productRepo;



//
//
@DeleteMapping("/orders/{orderId}")
    public String deleteOrderFromCart(@PathVariable("orderId") int orderId){
         Orders order=orderRepo.findById(orderId).orElse(null);
         orderRepo.delete(order);
         return "Order removed ";
}


    @GetMapping("/orders/{userId}")
    public List<Orders> getallorders(@PathVariable("userId") int userId){
        User user = userRepo.findById(userId).get();
       List<Orders> order=orderRepo.findByUser(user);
      return order;

    }

    @PostMapping("/{cartId}/orders")
    public String placeOrder(@PathVariable("cartId") int cartId){

    User user = userRepo.findById(cartId).get();
 Cart cart=cartRepo.findById(user.getCart().getCartId()).orElse(null);


 Orders orders=new Orders();
 double  totalPrice=cart.getTotalPrice();
// int quantity=cart.getQuantity();
 orders.setTotalPrice(totalPrice);
// orders.setQuantity(quantity);
orders.setUser(user);
List<Product> p=new ArrayList<>();
for(Product prod:cart.getProduct()){
    p.add(prod);
}

orders.setProducts(p);
orderRepo.save(orders);

//for clearing product from cart table when order is placed
cart.getProduct().clear();
//cart.setQuantity(0);
cart.setTotalPrice(0);
cartRepo.save(cart);

return "order placeed";

}

}
