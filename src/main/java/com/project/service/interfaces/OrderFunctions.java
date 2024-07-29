package com.project.service.interfaces;

import com.project.model.dto.OrderDTO;

import java.util.List;

public interface OrderFunctions {
    List<OrderDTO> viewOrders(Integer idUser);
}
