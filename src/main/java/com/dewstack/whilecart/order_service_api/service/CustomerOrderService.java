package com.dewstack.whilecart.order_service_api.service;

import com.dewstack.whilecart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.dewstack.whilecart.order_service_api.dto.response.CustomerOrderResponseDto;
import com.dewstack.whilecart.order_service_api.dto.response.paginate.CustomerOrderPaginateDto;

import java.util.List;

public interface CustomerOrderService {
    public void createOrder(CustomerOrderRequestDto requestDto);
    public void updateOrder(CustomerOrderRequestDto requestDto,String orderId);
    public void manageRemark(String remark,String orderId);
    public void manageStatus(String status,String orderId);
    public CustomerOrderResponseDto findOrderById(String orderId);
    public void deleteById(String orderId);
    public CustomerOrderPaginateDto searchAll(String text,int page, int pageSize);
}
