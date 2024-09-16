package com.project.controller;

import com.project.model.PaymentRequest;
import com.project.model.dto.OrderDTO;
import com.project.response.SuccessResponse;
import com.project.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(new SuccessResponse<>(orderService.viewOrdersForUser(idUser)), HttpStatus.OK);
    }

    @GetMapping("detail/{idOrder}")
    public ResponseEntity<SuccessResponse<OrderDTO>> getOrderDetails(@PathVariable Integer idOrder,
                                                                     HttpServletRequest request) {
        return new ResponseEntity<>(new SuccessResponse<>(orderService.getOrderDetails(idOrder)), HttpStatus.OK);
    }

    @PostMapping("/user/{idUser}/{idTicket}/{qta}")
    public ResponseEntity<SuccessResponse<Boolean>> createAndConfirmOrder(@PathVariable Integer idUser,
                                                                                @PathVariable Integer idTicket,
                                                                                @PathVariable int qta,
                                                                                @RequestBody PaymentRequest paymentRequest,
                                                                                HttpServletRequest request){

        return new ResponseEntity<>(new SuccessResponse<>(orderService.createAndConfirmOrder(idUser, idTicket, qta, paymentRequest.getPaymentType())), HttpStatus.OK);
    }



}
