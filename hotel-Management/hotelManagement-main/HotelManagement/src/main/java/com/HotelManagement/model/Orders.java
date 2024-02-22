package com.HotelManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;


    @ManyToOne
    @JsonIgnoreProperties("cart")
    private User user;



    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Product> products;


    private double totalPrice;

}
