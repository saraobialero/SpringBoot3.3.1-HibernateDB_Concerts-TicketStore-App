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
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<SuccessResponse<List<ProductDTO>>> getProducts(HttpServletRequest request){
        return new ResponseEntity<>(new SuccessResponse<>(productService.viewAvailableProductsFromNow()), HttpStatus.OK);
    }

    @GetMapping("/detail/{idProduct}")
    public ResponseEntity<SuccessResponse<ProductDTO>> getProductDetails(@PathVariable Integer idProduct,
                                                                         HttpServletRequest request) {
        ProductDTO productDTO = productService.getProducts(idProduct);
        return new ResponseEntity<>(new SuccessResponse<>(productDTO), HttpStatus.OK);
    }


}
