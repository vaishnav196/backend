package com.HotelManagement.Repository;

import com.HotelManagement.model.Orders;
import com.HotelManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Orders,Integer> {




    List<Orders> findByUser(User user);
}
