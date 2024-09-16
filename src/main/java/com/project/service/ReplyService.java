package com.project.service;

import com.project.exception.ReplyException;
import com.project.model.Reply;
import com.project.model.Seller;
import com.project.model.Ticket;
import com.project.model.dto.ReplyDTO;
import com.project.model.dto.SellerDTO;
import com.project.model.dto.TicketDTO;
import com.project.model.enums.ErrorCode;
import com.project.repository.ReplyRepository;
import com.project.response.ErrorResponse;
import com.project.service.interfaces.ReplyFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyService implements ReplyFunctions {

  //Constructor injection
  private final ReplyRepository replyRepository;
  @Autowired
  public ReplyService(ReplyRepository replyRepository) {
      this.replyRepository = replyRepository;
  }

    @Override
    public List<ReplyDTO> viewAvailableReplyFromNow() {
        List<Reply> entities = replyRepository.findAllFromNow();
        return entities.stream()
                .map(this::convertToReplyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReplyDTO getReplyDetails(Integer idReply) {
        Reply reply = replyRepository.findById(idReply)
                .orElseThrow(()-> new ReplyException(
                new ErrorResponse(ErrorCode.CNF, "Reply of concert not found with id: " + idReply)));
        return convertToReplyDTO(reply);
    }

    // Convert entity to DTO
    private ReplyDTO convertToReplyDTO(Reply reply) {
        return ReplyDTO.builder()
                .id(reply.getId())
                .replyDate(reply.getReplyDate())
                .ticket(convertToTicketDTO(reply.getTicket()))
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
                .seller(convertToSellerDTO(ticket.getSeller()))  // Aggiungi questa riga
                .build();
    }
}
