package com.project.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Integer id;
    private String city;
    private String band;
    private String reply;
    private int place;
    private LocalDate date;

    @JsonProperty("price")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;
}
