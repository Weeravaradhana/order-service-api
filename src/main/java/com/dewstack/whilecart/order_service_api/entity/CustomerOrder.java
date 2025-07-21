package com.dewstack.whilecart.order_service_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    @Column(name="total-amount",nullable = false)
    private double totalAmount;
    @Column(name = "user-id",nullable = false,length = 80)
    private String userId;
    @Column(length = 750)
    private String remark;
    @OneToMany(mappedBy = "customerOrder")
    private Set<OrderDetail> orderDetails = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;


}
