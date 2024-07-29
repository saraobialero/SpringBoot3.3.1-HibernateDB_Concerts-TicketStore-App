package com.project.controller;

import com.project.model.dto.ProductDTO;
import com.project.response.SuccessResponse;
import com.project.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/concerts")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<SuccessResponse<List<ProductDTO>>> getConcerts(HttpServletRequest request){
        return new ResponseEntity<>(new SuccessResponse<>(productService.viewAvailableProductsFromNow()), HttpStatus.OK);
    }

    @GetMapping("/detail/{idConcert}")
    public ResponseEntity<SuccessResponse<ProductDTO>> getConcertDetail(@PathVariable Integer idConcert,
                                                                        HttpServletRequest request) {
        ProductDTO productDTO = productService.getProducts(idConcert);
        return new ResponseEntity<>(new SuccessResponse<>(productDTO), HttpStatus.OK);
    }

    @PatchMapping("/detail/place/{idConcert}/{qta}")
    public ResponseEntity<SuccessResponse<Boolean>> updatePlace(@PathVariable Integer idConcert,
                                                                @PathVariable int qta,
                                                                HttpServletRequest request) {
        boolean updatedSuccessfully = productService.updateAvailablePlaceAfterOrders(idConcert, qta);
        return updatedSuccessfully
               ? new ResponseEntity<>(new SuccessResponse<>(updatedSuccessfully), HttpStatus.OK)
               : new ResponseEntity<>(new SuccessResponse<>(updatedSuccessfully), HttpStatus.BAD_REQUEST);
    }

}
