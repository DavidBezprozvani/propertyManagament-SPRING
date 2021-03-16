package com.example.realEstate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @ManyToOne
    private Owner owner;

    @Column(name = "market_value")
    private Double marketValue;

    private Double size;

    @Enumerated(EnumType.STRING)
    private Type type;

}
