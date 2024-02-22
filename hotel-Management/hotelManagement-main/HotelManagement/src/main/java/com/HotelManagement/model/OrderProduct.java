package com.HotelManagement.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Orders_products")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    

    @Column(nullable = true)
    private int productQuantity;

}
