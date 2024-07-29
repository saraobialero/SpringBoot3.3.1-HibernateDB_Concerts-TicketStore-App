package com.project.service;

import com.project.model.dto.TicketDTO;
import com.project.repository.TicketRepository;
import com.project.service.interfaces.TicketFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements TicketFunctions {

    //Constructor injection
    private final TicketRepository ticketRepository;
    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<TicketDTO> viewAvailableTicketsFromNow() {
        return null;
    }

    //Convert entity to DTO
    private TicketDTO convertToTicketDTO() {
        return null;
    }

}
