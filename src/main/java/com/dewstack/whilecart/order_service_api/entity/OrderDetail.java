package com.dewstack.whilecart.order_service_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "customer-order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "detail-id",unique = true,nullable = false,length = 80)
    private String detailId;
    @Column(name = "product-id",nullable = false,length = 80)
    private String productId;
    @Column(name = "qty",nullable = false)
    private int qty;
    @Column(name="unit-price",nullable = false,precision=10,scale=2)
    private double unitPrice;
    @Column(name="discount",precision=10,scale=2)
    private double discount;
    @ManyToOne
    @JoinColumn(name = "customer_order_id")//foreign key map
    private CustomerOrder customerOrder;
}
