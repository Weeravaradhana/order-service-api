package com.dewstack.whilecart.order_service_api.dto.response.paginate;

import com.dewstack.whilecart.order_service_api.dto.response.CustomerOrderResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerOrderPaginateDto {
    private long count;
    private List<CustomerOrderResponseDto> ordersDetails;
}
