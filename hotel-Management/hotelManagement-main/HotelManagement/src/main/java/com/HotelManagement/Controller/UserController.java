package com.HotelManagement.Controller;

import com.HotelManagement.Repository.CartRepo;
import com.HotelManagement.Repository.OrderRepo;
import com.HotelManagement.Repository.UserRepo;
import com.HotelManagement.model.Cart;
import com.HotelManagement.model.Orders;
import com.HotelManagement.model.Product;
import com.HotelManagement.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("http://localhost:4200")
@RestController
public class UserController {
@Autowired
private  UserRepo userRepo;


@Autowired
private CartRepo cartRepo;


@Autowired
private OrderRepo orderRepo;
@Autowired
private PasswordEncoder passwordEncoder;
@PostMapping("/user")
public String adduser(@RequestBody User us ){
    User user=userRepo.save(us);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepo.save(user);
   return "User Successfully Created";
}

//@GetMapping("/user")
//public List<User> getUsers(){
//    List<User> getRegisteredUser=userRepo.findAll();
//    return getRegisteredUser;
//}


//@GetMapping("/user/{userId}")
//public User getNewUser(@PathVariable("userId") int userId){
//    User newuser=userRepo.findById(userId).orElse(new User());
//    return newuser;
//}


    @GetMapping("/user")
    public User getNewUser(HttpServletRequest request){
        String token= (String) request.getAttribute("jwtToken");
        Claims claims = Jwts.parser()
                .setSigningKey("afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf")
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims.getSubject());

        User us= userRepo.findFirstByUsername(claims.getSubject());
//    User newuser=userRepo.findById(userId).orElse(new User());
        return us;
    }


@DeleteMapping("/user/{userId}")
public Optional<User> deleteuser(@PathVariable("userId") int userId,Orders order){
Optional<User> us=userRepo.findById(userId);
userRepo.deleteById(userId);
return us;

}



}
