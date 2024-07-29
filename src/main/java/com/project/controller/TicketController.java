package com.project.controller;

import com.project.model.dto.TicketDTO;
import com.project.response.SuccessResponse;
import com.project.service.TicketService;
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
@RequestMapping("/api/tickets")
public class TicketController {

    private TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/")
    public ResponseEntity<SuccessResponse<List<TicketDTO>>> getTickets(HttpServletRequest request){
        return new ResponseEntity<>(new SuccessResponse<>(ticketService.viewAvailableTicketsFromNow()), HttpStatus.OK);
    }

    @GetMapping("/details/{idTicket}")
    public ResponseEntity<SuccessResponse<TicketDTO>> getTicketDetails(@PathVariable Integer idTicket,
                                                                        HttpServletRequest request) {
        TicketDTO ticketDTO = null;
        return new ResponseEntity<>(new SuccessResponse<>(ticketDTO), HttpStatus.OK);
    }


}
