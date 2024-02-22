package com.HotelManagement.Repository;

import com.HotelManagement.model.Cart;
import com.HotelManagement.model.CartProduct;
import com.HotelManagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepo extends JpaRepository<CartProduct,Integer> {
     CartProduct findByCartIdAndProductId(Cart c, Product p);
}
