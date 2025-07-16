package com.dewstack.whilecart.order_service_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "customer-order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "status-id",unique = true,nullable = false,length = 80)
    private String statusId;
    @Column(name = "status",nullable = false,length = 45)
    private String status;
    @OneToMany(mappedBy = "orderStatus")
    private Set<CustomerOrder> customerOrders = new HashSet<>();
}
