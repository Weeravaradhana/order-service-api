package com.dewstack.whilecart.order_service_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class CustomerOrder {
    @Id
    @Column(name = "order-id",unique = true,nullable = false,length = 80)
    private String orderId;
    @Column(name = "order-date",nullable = false,columnDefinition = "DATETIME")
    private Date orderDate;
    @Column(name="total-amount",nullable = false,precision=10,scale=2)
    private double totalAmount;
    @Column(name = "user-id",nullable = false,length = 80)
    private String userId;
    @Column(length = 750)
    private String remark;
}
