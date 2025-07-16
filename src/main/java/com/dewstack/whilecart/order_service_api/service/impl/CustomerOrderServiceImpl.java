package com.dewstack.whilecart.order_service_api.service.impl;

import com.dewstack.whilecart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.dewstack.whilecart.order_service_api.dto.request.OrderDetailRequestDto;
import com.dewstack.whilecart.order_service_api.entity.CustomerOrder;
import com.dewstack.whilecart.order_service_api.entity.OrderDetail;
import com.dewstack.whilecart.order_service_api.entity.OrderStatus;
import com.dewstack.whilecart.order_service_api.repo.CustomerOrderRepo;
import com.dewstack.whilecart.order_service_api.repo.OrderStatusRepo;
import com.dewstack.whilecart.order_service_api.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {
    private final CustomerOrderRepo orderRepo;
    private final OrderStatusRepo statusRepo;
    @Override
    public void createOrder(CustomerOrderRequestDto requestDto) {

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setOrderId(UUID.randomUUID().toString());
        customerOrder.setOrderDate(requestDto.getOrderDate());
        customerOrder.setRemark(" ");
        customerOrder.setTotalAmount(requestDto.getTotalAmount());
        customerOrder.setUserId(requestDto.getUserId());
        customerOrder.setOrderDetails(requestDto.getOrderDetails().stream().map(e->createOrderDetail(e,customerOrder)).collect(Collectors.toSet()));
        customerOrder.setOrderStatus(statusRepo.findByStatus("PENDING").orElseThrow(()-> new RuntimeException("Order Status Not Found.so you can place  an order or please contact admin")));
        orderRepo.save(customerOrder);

    }

    private OrderDetail createOrderDetail(OrderDetailRequestDto requestDto, CustomerOrder order) {
        if (requestDto == null) {
            return null;
        }
        return OrderDetail.builder()
                .detailId(UUID.randomUUID().toString())
                .unitPrice(requestDto.getUnitPrice())
                .discount(requestDto.getDiscount())
                .qty(requestDto.getQty())
                .customerOrder(order)
                .build();
    }

}
