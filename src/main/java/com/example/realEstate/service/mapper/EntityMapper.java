package com.example.realEstate.service.mapper;

import com.example.realEstate.entity.Property;
import com.example.realEstate.entity.dto.PropertyDTO;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    public PropertyDTO convertPropertyEntityToDTO(Property property, Long ownerId) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(property.getId());
        propertyDTO.setAddress(property.getAddress());
        propertyDTO.setSize(property.getSize());
        propertyDTO.setMarketValue(property.getMarketValue());
        propertyDTO.setOwnerId(property.getOwner().getId());
        propertyDTO.setType(property.getType().name());
        return propertyDTO;
    }

    public PropertyDTO convertPropertyEntityToDTO(Property property) {
        return convertPropertyEntityToDTO(property, property.getOwner().getId());
    }
}
