package com.project.service;

import com.project.exception.ProductException;
import com.project.model.Product;
import com.project.model.dto.ProductDTO;
import com.project.model.enums.ErrorCode;
import com.project.repository.ProductRepository;
import com.project.response.ErrorResponse;
import com.project.service.interfaces.ProductFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductFunctions {
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> viewAvailableProductsFromNow() {
        List<Product> entities = productRepository.findAllFromNow();
        return entities.stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProducts(Integer idProduct) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(()-> new ProductException(
                new ErrorResponse(ErrorCode.CNF, "Concert not found with id: " + idProduct)));
        return convertToProductDTO(product);
    }

    @Override
    public boolean updateAvailablePlaceAfterOrders(Integer idProduct, int qta) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(()-> new ProductException(
                                  new ErrorResponse(ErrorCode.CNF, "Concert not found with id: " + idProduct)));

        if(product.getAvailablePlace() < qta) {
            throw new ProductException(
                  new ErrorResponse(ErrorCode.CSO, "Concert Sold out with id: " + idProduct));
        }

        product.setAvailablePlace(product.getAvailablePlace() - qta);
        productRepository.save(product);

        return true;
    }

    public ProductDTO convertToProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .city(product.getCity())
                .band(product.getBand())
                .reply(product.getReply())
                .place(product.getAvailablePlace())
                .date(product.getDate())
                .price(new BigDecimal(String.valueOf(product.getPrice())))
                .build();
    }
}
