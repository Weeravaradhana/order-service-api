package com.dewstack.whilecart.order_service_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderDetailRequestDto {
    private String productId;
    private int qty;
    private double unitPrice;
    private double discount;

}
