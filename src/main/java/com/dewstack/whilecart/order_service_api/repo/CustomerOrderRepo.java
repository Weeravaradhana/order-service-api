package com.dewstack.whilecart.order_service_api.repo;

import com.dewstack.whilecart.order_service_api.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepo extends JpaRepository<CustomerOrder,String> {
}
