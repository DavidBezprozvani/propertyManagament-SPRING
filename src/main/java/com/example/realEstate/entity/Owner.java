package com.example.realEstate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @Column(name = "social_number")
    private String socialNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Property> properties;

    public Owner(Long id, String name, String surname, String socialNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.socialNumber = socialNumber;
    }
}
