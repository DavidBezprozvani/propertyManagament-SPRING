package com.example.realEstate.service.dao;

import com.example.realEstate.entity.dto.PropertyDTO;

import java.util.List;

public interface PropertyService {

    List<PropertyDTO> getAllProperties();

    PropertyDTO getSinglePropertyById(Long propertyId);

    PropertyDTO createProperty(PropertyDTO propertyDTO);

    PropertyDTO updateProperty(PropertyDTO propertyDTO);

    void deleteProperty(Long id);
}
