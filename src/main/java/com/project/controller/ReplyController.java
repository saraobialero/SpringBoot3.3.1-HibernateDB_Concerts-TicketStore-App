package com.project.controller;

import com.project.model.dto.ReplyDTO;
import com.project.response.SuccessResponse;
import com.project.service.ReplyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/replies")
public class ReplyController {

    private final ReplyService replyService;
    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping("/")
    public ResponseEntity<SuccessResponse<List<ReplyDTO>>> getReplies(HttpServletRequest request){
        return new ResponseEntity<>(new SuccessResponse<>(replyService.viewAvailableReplyFromNow()), HttpStatus.OK);
    }

    @GetMapping("/detail/{idReply}")
    public ResponseEntity<SuccessResponse<ReplyDTO>> getProductDetails(@PathVariable Integer idReply,
                                                                       HttpServletRequest request) {
        ReplyDTO replyDTO = replyService.getReplyDetails(idReply);
        return new ResponseEntity<>(new SuccessResponse<>(replyDTO), HttpStatus.OK);
    }


}
