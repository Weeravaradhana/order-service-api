package com.dewstack.whilecart.order_service_api.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity(name = "customer-detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {
    @Id
    @Column(name = "detail-id",unique = true,nullable = false,length = 80)
    private String detailId;
    @Column(name = "product-id",nullable = false,length = 80)
    private String productId;
    @Column(name = "qty",nullable = false)
    private int qty;
    @Column(name="unit-price",nullable = false)
    private double unitPrice;
    @Column(name="discount")
    private double discount;
    @ManyToOne
    @JoinColumn(name = "customer_order_id")//foreign key map
    private CustomerOrder customerOrder;
}
