package com.dewstack.whilecart.order_service_api.service.impl;

import com.dewstack.whilecart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.dewstack.whilecart.order_service_api.dto.request.OrderDetailRequestDto;
import com.dewstack.whilecart.order_service_api.dto.response.CustomerOrderResponseDto;
import com.dewstack.whilecart.order_service_api.dto.response.OrderDetailResponseDto;
import com.dewstack.whilecart.order_service_api.dto.response.paginate.CustomerOrderPaginateDto;
import com.dewstack.whilecart.order_service_api.entity.CustomerOrder;
import com.dewstack.whilecart.order_service_api.entity.OrderDetail;
import com.dewstack.whilecart.order_service_api.entity.OrderStatus;
import com.dewstack.whilecart.order_service_api.exception.EntryNotFoundException;
import com.dewstack.whilecart.order_service_api.repo.CustomerOrderRepo;
import com.dewstack.whilecart.order_service_api.repo.OrderStatusRepo;
import com.dewstack.whilecart.order_service_api.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
        customerOrder.setOrderDetails(requestDto.getOrderDetails()
                .stream()
                .map(e->createOrderDetail(e,customerOrder))
                .collect(Collectors.toSet()));
        customerOrder.setOrderStatus(statusRepo
                .findByStatus("PENDING")
                .orElseThrow(()-> new EntryNotFoundException("Order Status Not Found.so you can place  an order or please contact admin"))
        );
        orderRepo.save(customerOrder);

    }

    @Override
    public void updateOrder(CustomerOrderRequestDto requestDto, String orderId) {
        CustomerOrder customerOrder = orderRepo
                .findById(orderId)
                .orElseThrow(()-> new EntryNotFoundException(String.format("Order Id %s not found", orderId)));
        customerOrder.setOrderDate(requestDto.getOrderDate());
        customerOrder.setTotalAmount(requestDto.getTotalAmount());
        orderRepo.save(customerOrder);
    }

    @Override
    public void manageRemark(String remark, String orderId) {
        CustomerOrder customerOrder = orderRepo
                .findById(orderId)
                .orElseThrow(()-> new EntryNotFoundException(String.format("Order Id %s not found", orderId)));
        customerOrder.setRemark(remark);
        orderRepo.save(customerOrder);
    }

    @Override
    public void manageStatus(String status, String orderId) {
        CustomerOrder customerOrder = orderRepo
                .findById(orderId)
                .orElseThrow(()-> new EntryNotFoundException(String.format("Order Id %s not found", orderId)));
        OrderStatus orderStatus = statusRepo
                .findByStatus(status)
                .orElseThrow(() -> new EntryNotFoundException("Order Status Not Found.so you can place  an order or please contact admin"));
        customerOrder.setOrderStatus(orderStatus);
        orderRepo.save(customerOrder);
    }

    @Override
    public CustomerOrderResponseDto findOrderById(String orderId) {
       CustomerOrder customerOrder = orderRepo.findById(orderId)
               .orElseThrow(()-> new EntryNotFoundException(String.format("Order Id %s not found", orderId)));
        return toCustomerOrderResponseDto(customerOrder);

    }

    @Override
    public void deleteById(String orderId) {
        CustomerOrder customerOrder = orderRepo.findById(orderId)
                .orElseThrow(()-> new EntryNotFoundException(String.format("Order Id %s not found", orderId)));
        orderRepo.delete(customerOrder);
    }

    @Override
    public CustomerOrderPaginateDto searchAll(String text, int page, int pageSize) {
        return CustomerOrderPaginateDto
                .builder()
                .count(orderRepo.searchCount(text))
                .ordersDetails(orderRepo.searchAll(text, PageRequest.of(page,pageSize))
                        .stream().map(this::toCustomerOrderResponseDto).collect(Collectors.toList()))
                .build();
    }


    private CustomerOrderResponseDto toCustomerOrderResponseDto(CustomerOrder customerOrder) {
        if(customerOrder == null){
            return null;

        }
        return CustomerOrderResponseDto.builder()
                .orderId(customerOrder.getOrderId())
                .orderDate(customerOrder.getOrderDate())
                .userId(customerOrder.getUserId())
                .totalAmount(customerOrder.getTotalAmount())
                .OrderDetails(customerOrder.getOrderDetails()
                        .stream()
                        .map(this::toOrderDetailResponseDto)
                        .collect(Collectors.toList()))
                .remark(customerOrder.getRemark())
                .status(customerOrder.getOrderStatus().getStatus())
                .build();
    }

    private OrderDetailResponseDto toOrderDetailResponseDto(OrderDetail orderDetail) {
        if(orderDetail == null){
            return null;
        }

       return OrderDetailResponseDto.builder()
               .detailId(orderDetail.getDetailId())
               .productId(orderDetail.getProductId())
               .qty(orderDetail.getQty())
               .unitPrice(orderDetail.getUnitPrice())
               .discount(orderDetail.getDiscount())
               .build();
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
