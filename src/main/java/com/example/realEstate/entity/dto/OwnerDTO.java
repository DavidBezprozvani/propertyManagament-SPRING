package com.example.realEstate.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {

    private Long id;

    private String name;

    private String surname;

    private String socialNumber;
}
