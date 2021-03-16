package com.example.realEstate.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDTO {

    private Long id;

    private String address;

    private Double marketValue;

    private Double size;

    private String type;

    private Long ownerId;
}
