package com.project.service.interfaces;

import com.project.model.dto.TicketDTO;

import java.util.List;

public interface TicketFunctions {
    List<TicketDTO> viewAvailableTickets();
    TicketDTO viewTicketDetails(Integer idTicket);
}
