package com.project.service;

import com.project.exception.TicketException;
import com.project.model.Seller;
import com.project.model.Ticket;
import com.project.model.dto.SellerDTO;
import com.project.model.dto.TicketDTO;
import com.project.model.enums.ErrorCode;
import com.project.repository.TicketRepository;
import com.project.response.ErrorResponse;
import com.project.service.interfaces.TicketFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService implements TicketFunctions {

    //Constructor injection
    private final TicketRepository ticketRepository;
    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<TicketDTO> viewAvailableTickets() {
        List<Ticket> tickets = ticketRepository.findAvailableTickets();
        return tickets.stream()
                .map(this::convertToTicketDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TicketDTO viewTicketDetails(Integer idTicket) {
        Ticket ticket = ticketRepository.findById(idTicket)
                        .orElseThrow(() ->
                                    new TicketException(new ErrorResponse(ErrorCode.TNF, "Ticket not found with id: " + idTicket)));
        return convertToTicketDTO(ticket);
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
