package com.project.service;

import com.project.exception.ConcertException;
import com.project.model.Concert;
import com.project.model.dto.ConcertDTO;
import com.project.model.enums.ErrorCode;
import com.project.repository.ConcertRepository;
import com.project.response.ErrorResponse;
import com.project.service.interfaces.ConcertFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConcertService implements ConcertFunctions {

    @Autowired
    private ConcertRepository concertRepository;

    @Override
    public List<ConcertDTO> viewAvailableConcertsFromNow() {
        List<Concert> concerts = concertRepository.findAllFromNow();
        return concerts.stream()
                .map(this::convertToConcertDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ConcertDTO getConcert(Integer idConcert) {
        Concert concert = concertRepository.findById(idConcert)
                .orElseThrow(()-> new ConcertException(
                new ErrorResponse(ErrorCode.CNF, "Concert not found with id: " + idConcert)));
        return convertToConcertDTO(concert);
    }

    @Override
    public boolean updateAvailablePlaceAfterPrenotation(Integer idConcert, int qta) {
        Concert concert = concertRepository.findById(idConcert)
                .orElseThrow(()-> new ConcertException(
                                  new ErrorResponse(ErrorCode.CNF, "Concert not found with id: " + idConcert)));

        if(concert.getAvailablePlace() < qta) {
            throw new ConcertException(
                  new ErrorResponse(ErrorCode.CSO, "Concert Sold out with id: " + idConcert));
        }

        concert.setAvailablePlace(concert.getAvailablePlace() - qta);
        concertRepository.save(concert);

        return true;
    }

    public ConcertDTO convertToConcertDTO(Concert concert) {
        return ConcertDTO.builder()
                .id(concert.getId())
                .city(concert.getCity())
                .band(concert.getBand())
                .reply(concert.getReply())
                .place(concert.getAvailablePlace())
                .date(concert.getDate())
                .price(new BigDecimal(String.valueOf(concert.getPrice())))
                .build();
    }
}
