package com.project.service.interfaces;

import com.project.model.dto.ConcertDTO;

import java.util.List;

public interface ConcertFunctions {
    List<ConcertDTO> viewAvailableConcertsFromNow();
    ConcertDTO getConcert(Integer idConcert);
    boolean updateAvailablePlaceAfterPrenotation(Integer idConcert, int qta);
}
