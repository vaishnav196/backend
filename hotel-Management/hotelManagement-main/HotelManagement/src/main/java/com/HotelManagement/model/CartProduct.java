package com.HotelManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_product")
@Entity
public class CartProduct {



    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;

    @Column(name = "cart_cart_id")
    private int cartId;

    @Column(name = "product_product_id")
    private int productId;

    @Column(nullable = true)
    private int productQuantity;

}
