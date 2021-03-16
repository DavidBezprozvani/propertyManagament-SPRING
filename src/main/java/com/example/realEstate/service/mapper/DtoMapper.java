package com.example.realEstate.service.mapper;


import com.example.realEstate.entity.Property;
import com.example.realEstate.entity.Type;
import com.example.realEstate.entity.dto.PropertyDTO;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    public Property convertPropertyDtoToEntity(PropertyDTO propertyDTO) {
        Property property = new Property();
        property.setId(propertyDTO.getId());
        property.setAddress(propertyDTO.getAddress());
        property.setSize(propertyDTO.getSize());
        convertType(propertyDTO, property);
        property.getOwner().setId(propertyDTO.getOwnerId());
        return property;
    }

    private void convertType(PropertyDTO propertyDTO, Property property) {
        if (propertyDTO.getType().equalsIgnoreCase(Type.HOUSE.name())) {
            property.setType(Type.HOUSE);
        }
        else if (propertyDTO.getType().equalsIgnoreCase(Type.APARTMENT.name())) {
            property.setType(Type.APARTMENT);
        }
        else if (propertyDTO.getType().equalsIgnoreCase(Type.COMMERCIAL.name())) {
            property.setType(Type.COMMERCIAL);
        }
        if (propertyDTO.getType().equalsIgnoreCase(Type.INDUSTRIAL.name())) {
            property.setType(Type.INDUSTRIAL);
        }
    }
}
