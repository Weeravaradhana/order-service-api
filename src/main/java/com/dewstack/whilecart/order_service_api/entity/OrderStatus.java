package com.dewstack.whilecart.order_service_api.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "order-status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {
    @Id
    @Column(name = "status-id",unique = true,nullable = false,length = 80)
    private String statusId;
    @Column(name = "status",nullable = false,length = 80,unique = true)
    private String status;
    @OneToMany(mappedBy = "orderStatus")
    private Set<CustomerOrder> customerOrders = new HashSet<>();
}
