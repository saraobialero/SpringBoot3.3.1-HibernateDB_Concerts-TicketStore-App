package com.project.service.interfaces;

import com.project.model.dto.ProductDTO;

import java.util.List;

public interface ProductFunctions {
    List<ProductDTO> viewAvailableProductsFromNow();
    ProductDTO getProducts(Integer idProduct);
}
