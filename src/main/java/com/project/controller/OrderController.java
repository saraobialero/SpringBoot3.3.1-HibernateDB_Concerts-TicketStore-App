package com.project.controller;

import com.project.model.dto.OrderDTO;
import com.project.model.dto.ProductDTO;
import com.project.repository.OrderRepository;
import com.project.response.SuccessResponse;
import com.project.service.OrderService;
import com.project.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{idUser}")
    public ResponseEntity<SuccessResponse<List<OrderDTO>>> getOrders(@PathVariable Integer idUser,
                                                                     HttpServletRequest request) {
        List<OrderDTO> orderDTO = null;
        return new ResponseEntity<>(new SuccessResponse<>(orderService.viewOrders(idUser)), HttpStatus.OK);
    }


}
