package com.HotelManagement.Repository;

import com.HotelManagement.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo  extends JpaRepository<Cart,Integer> {

}
