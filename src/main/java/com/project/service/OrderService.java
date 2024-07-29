package com.project.service;

import com.project.model.dto.OrderDTO;
import com.project.repository.OrderRepository;
import com.project.service.interfaces.OrderFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements OrderFunctions {

    //Constructor injection
    private final OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDTO> viewOrders(Integer idUser) {
        return null;
    }

    //Convert entity to DTO
    private OrderDTO convertToOrderDTO() {
        return null;
    }

}
