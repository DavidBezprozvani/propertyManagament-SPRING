package com.example.realEstate.service.mapper;


import com.example.realEstate.entity.Owner;
import com.example.realEstate.entity.Property;
import com.example.realEstate.entity.Type;
import com.example.realEstate.entity.dto.PropertyDTO;
import com.example.realEstate.repository.OwnerRepository;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    private OwnerRepository ownerRepository;

    public DtoMapper(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Property convertPropertyDtoToEntity(PropertyDTO propertyDTO) {
        Property property = new Property();
        Owner owner = ownerRepository.getOne(propertyDTO.getOwnerId());
        property.setId(propertyDTO.getId());
        property.setOwner(owner);
        property.setAddress(propertyDTO.getAddress());
        property.setMarketValue(propertyDTO.getMarketValue());
        property.setSize(propertyDTO.getSize());
        convertType(propertyDTO, property);
        return property;
    }

    public void convertType(PropertyDTO propertyDTO, Property property) {
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
