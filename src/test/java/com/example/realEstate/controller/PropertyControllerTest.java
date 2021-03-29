package com.example.realEstate.controller;

import com.example.realEstate.entity.Owner;
import com.example.realEstate.entity.Property;
import com.example.realEstate.entity.Type;
import com.example.realEstate.repository.OwnerRepository;
import com.example.realEstate.repository.PropertyRepository;
import com.example.realEstate.service.PropertyServiceImpl;
import com.example.realEstate.service.mapper.DtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@WebMvcTest(PropertyController.class)
class PropertyControllerTest {

    @InjectMocks
    private PropertyController propertyController;

    @MockBean
    private PropertyServiceImpl propertyServiceImpl;

    @Mock
    private PropertyRepository propertyRepository;
    @Mock
    private OwnerRepository ownerRepository;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DtoMapper dtoMapper;


    @BeforeEach
    public void setUp() {
        propertyController = new PropertyController(propertyServiceImpl);

    }

    @Test
    void getAllProperties() {
        List<Property> properties = new ArrayList<>();
        properties.add(new Property(1L, "Mock address 1", new Owner(1L, "John", "Doe", "11111"), BigDecimal.valueOf(100.00), BigDecimal.valueOf(35.22), Type.HOUSE));
        properties.add(new Property(2L, "Mock address 2", new Owner(2L, "Don", "Johnson", "22222"), BigDecimal.valueOf(200.00), BigDecimal.valueOf(45.22), Type.COMMERCIAL));

        Mockito.when(propertyRepository.findAll()).thenReturn(properties);
        List<Property> expected = propertyController.getAllProperties()
                .stream()
                .map(property -> dtoMapper.convertPropertyDtoToEntity(property))
                .collect(Collectors.toList());

        assertEquals(expected, properties);
        verify(propertyRepository.findAll());

    }



    @Test
    void getSinglePropertyById() {



    }

    @Test
    void getAllPropertiesByOwner() {
    }

    @Test
    void saveProperty() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }
}