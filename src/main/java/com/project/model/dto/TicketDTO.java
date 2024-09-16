package com.project.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {

    private Integer id;
    private String city;
    private String location;
    private String band;
    @JsonProperty("price")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;
    private int qta;
    private SellerDTO seller;

}
