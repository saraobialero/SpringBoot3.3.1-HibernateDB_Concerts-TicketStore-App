package com.project.service.interfaces;

import com.project.model.dto.ProductDTO;

import java.util.List;

public interface ProductFunctions {
    List<ProductDTO> viewAvailableConcertsFromNow();
    ProductDTO getConcert(Integer idConcert);
    boolean updateAvailablePlaceAfterPrenotation(Integer idConcert, int qta);
}
