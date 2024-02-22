package com.HotelManagement.Repository;

import com.HotelManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findFirstByUsername(String username);




}


