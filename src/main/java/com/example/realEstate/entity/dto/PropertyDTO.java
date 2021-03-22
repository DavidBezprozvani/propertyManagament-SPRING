package com.example.realEstate.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDTO {

    private Long id;

    private String address;

    private BigDecimal marketValue;

    private BigDecimal size;

    private String type;

    private Long ownerId;
}
