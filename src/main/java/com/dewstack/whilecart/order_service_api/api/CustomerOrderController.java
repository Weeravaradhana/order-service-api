package com.dewstack.whilecart.order_service_api.api;

import com.dewstack.whilecart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.dewstack.whilecart.order_service_api.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer-order")
@RequiredArgsConstructor
public class CustomerOrderController {
    private final CustomerOrderService customerOrderService;

    @PostMapping("/business")
    public String create(@RequestBody CustomerOrderRequestDto request) {
        customerOrderService.createOrder(request);
    }

    @GetMapping("/visitors/find-by-id/{id}")
    public String findById(@PathVariable String id) {
        customerOrderService.findOrderById(id);
    }

    @PutMapping("/business/update-order/{id}")
    public String update(@RequestBody CustomerOrderRequestDto request,@PathVariable String id) {
        customerOrderService.updateOrder(request,id);
    }

    @PutMapping("/business/update-remark/{id}")
    public String manageRemark(@RequestParam String remark,@PathVariable String id) {
        customerOrderService.manageRemark(remark,id);
    }

    @PutMapping("/business/update-status/{id}")
    public String manageStatus (@RequestParam String status,@PathVariable String id) {
        customerOrderService.manageStatus(status,id);
    }


    @DeleteMapping("/business/delete-by-id/{id}")
    public String deleteById(@PathVariable String id) {
        customerOrderService.deleteById(id);
    }

    @GetMapping("/visitors/search-all")
    public String searchAll(@RequestParam String searchText,@RequestParam int page,@RequestParam int pageSize) {
        customerOrderService.searchAll(searchText,page,pageSize);
    }



}
