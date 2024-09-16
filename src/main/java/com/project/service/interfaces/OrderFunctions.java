package com.project.service.interfaces;

import com.project.model.dto.OrderDTO;
import com.project.model.enums.PaymentType;

import java.util.List;

public interface OrderFunctions {
    boolean createAndConfirmOrder(Integer idUser, Integer idReply,  int qta, PaymentType paymentType);
    List<OrderDTO> viewOrdersForUser(Integer idUser);
    OrderDTO getOrderDetails(Integer idOrder);
}
