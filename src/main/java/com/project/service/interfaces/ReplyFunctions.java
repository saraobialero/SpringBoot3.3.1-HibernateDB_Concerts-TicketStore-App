package com.project.service.interfaces;

import com.project.model.dto.ReplyDTO;

import java.util.List;

public interface ReplyFunctions {
    List<ReplyDTO> viewAvailableReplyFromNow();
    ReplyDTO getReplyDetails(Integer idReply);

}
