package com.dewstack.whilecart.order_service_api.util;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StandardResponseDto {
    private int status;
    private String message;
    private Object data;


}
