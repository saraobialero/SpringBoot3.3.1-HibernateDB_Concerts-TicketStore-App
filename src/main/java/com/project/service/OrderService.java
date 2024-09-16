package com.project.service;

import com.project.exception.OrderException;
import com.project.exception.ReplyException;
import com.project.exception.TicketException;
import com.project.exception.UserException;
import com.project.model.*;
import com.project.model.dto.OrderDTO;
import com.project.model.dto.ReplyDTO;
import com.project.model.dto.SellerDTO;
import com.project.model.dto.TicketDTO;
import com.project.model.enums.ErrorCode;
import com.project.model.enums.PaymentType;
import com.project.repository.OrderRepository;
import com.project.repository.ReplyRepository;
import com.project.repository.TicketRepository;
import com.project.repository.UserRepository;
import com.project.response.ErrorResponse;
import com.project.service.interfaces.OrderFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderFunctions {

    //Constructor injection
    private final OrderRepository orderRepository;
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        ReplyRepository replyRepository,
                        UserRepository userRepository,
                        TicketRepository ticketRepository) {
        this.orderRepository = orderRepository;
        this.replyRepository = replyRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public boolean createAndConfirmOrder(Integer idUser, Integer idTicket, int qta, PaymentType paymentType) {

        //Find Ticket
        Ticket ticket = ticketRepository.findById(idTicket)
                .orElseThrow(() -> new TicketException(
                                new ErrorResponse(ErrorCode.CNF, "Ticket not found with id: " + idTicket)
                        )
                );

        //Find user
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new UserException(
                                new ErrorResponse(ErrorCode.CNF, "User not found with id: " + idUser)
                        )
                );

        //Check if there are enough tickets
        if (qta > ticket.getQta()) {
            throw new TicketException(
                    new ErrorResponse(ErrorCode.CNF, "Not enough tickets available")
            );
        }


        //Create or update order
        Order order = orderRepository.findByUserIdAndTicketId(idUser, idTicket)
                .orElseGet(() -> {
                    Order newOrder = new Order();
                    newOrder.setUser(user);
                    newOrder.setTicket(ticket);
                    newOrder.setQta(0);
                    newOrder.setPaymentType(paymentType);
                    return newOrder;
                });

        //Update old order
        order.setQta(order.getQta() + qta);

        //Save order
        orderRepository.save(order);

        //Update ticket quantity and save
        ticket.setQta(ticket.getQta() - qta);
        ticketRepository.save(ticket);
        return true;
    }

    @Override
    public List<OrderDTO> viewOrdersForUser(Integer idUser) {
        List<Order> order = orderRepository.findByUserId(idUser);
        if (order.isEmpty()) {
            throw new OrderException(
                    new ErrorResponse(ErrorCode.CNF, "No orders found for user with id: " + idUser)
            );
        }
        return order.stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    }


    @Override
    public OrderDTO getOrderDetails(Integer idOrder) {
        Order order = orderRepository.findById(idOrder).orElseThrow(() -> new OrderException(
                new ErrorResponse(ErrorCode.CNF, "Order not found with id: " + idOrder)
        ));
        return convertToOrderDTO(order);
    }

    //Convert entities to DTO
    private OrderDTO convertToOrderDTO(Order order) {
        return OrderDTO
                .builder()
                .id(order.getId())
                .paymentType(order.getPaymentType())
                .qta(order.getQta())
                .ticket(convertToTicketDTO(order.getTicket()))
                .build();
    }

    private SellerDTO convertToSellerDTO(Seller seller) {
        if (seller == null) {
            return null;
        }
        return SellerDTO.builder()
                .id(seller.getId())
                .companyName(seller.getCompanyName())
                .build();
    }

    private TicketDTO convertToTicketDTO(Ticket ticket) {
        if (ticket == null) {
            return null;
        }
        return TicketDTO.builder()
                .id(ticket.getId())
                .city(ticket.getCity())
                .location(ticket.getLocation())
                .band(ticket.getBand())
                .price(ticket.getPrice())
                .qta(ticket.getQta())
                .seller(convertToSellerDTO(ticket.getSeller()))
                .build();
    }
}
