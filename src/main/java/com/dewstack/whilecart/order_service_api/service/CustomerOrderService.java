package com.dewstack.whilecart.order_service_api.service;

import com.dewstack.whilecart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.dewstack.whilecart.order_service_api.dto.response.CustomerOrderResponseDto;

public interface CustomerOrderService {
    public void createOrder(CustomerOrderRequestDto requestDto);
    public CustomerOrderResponseDto findOrderById(String orderId);
}
