package com.example.realEstate.service;

import com.example.realEstate.entity.Owner;
import com.example.realEstate.entity.Property;
import com.example.realEstate.entity.dto.PropertyDTO;
import com.example.realEstate.exceptions.EntityNotFoundException;
import com.example.realEstate.repository.OwnerRepository;
import com.example.realEstate.repository.PropertyRepository;
import com.example.realEstate.service.mapper.DtoMapper;
import com.example.realEstate.service.mapper.EntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;
    private EntityMapper entityMapper;
    private DtoMapper dtoMapper;
    private OwnerRepository ownerRepository;

    public PropertyService(PropertyRepository propertyRepository, EntityMapper entityMapper, DtoMapper dtoMapper, OwnerRepository ownerRepository) {
        this.propertyRepository = propertyRepository;
        this.entityMapper = entityMapper;
        this.dtoMapper = dtoMapper;
        this.ownerRepository = ownerRepository;
    }


    public List<PropertyDTO> getAllProperties() {
        return propertyRepository.findAll()
                .stream()
                .map(property -> entityMapper.convertPropertyEntityToDTO(property))
                .collect(Collectors.toList());
    }

    public PropertyDTO getSinglePropertyById(Long propertyId) {
        if (propertyId == null) {
            throw new EntityNotFoundException(propertyId);
        }
        return entityMapper.convertPropertyEntityToDTO(propertyRepository.getOne(propertyId));
    }

    public List<PropertyDTO> getAllPropertiesByOwner(Long ownerId) {
        if (ownerId == null) {
            throw new EntityNotFoundException(ownerId);
        }
        return propertyRepository.findByOwner(ownerId)
                .stream()
                .map(property -> entityMapper.convertPropertyEntityToDTO(property))
                .collect(Collectors.toList());
    }

    public PropertyDTO createProperty(PropertyDTO propertyDTO) {
        Property property = dtoMapper.convertPropertyDtoToEntity(propertyDTO);
        Property savedProperty = propertyRepository.save(property);
        return entityMapper.convertPropertyEntityToDTO(savedProperty);

    }

    public PropertyDTO updateProperty(PropertyDTO propertyDTO) {
        Long id = propertyDTO.getId();
        if (id == null) {
            throw new EntityNotFoundException(id);
        }
        Property property = propertyRepository.getOne(id);
        property.setMarketValue(propertyDTO.getMarketValue());
        property.setSize(propertyDTO.getSize());
        property = dtoMapper.convertPropertyDtoToEntity(propertyDTO);
        Property updatedProperty = propertyRepository.save(property);
        return entityMapper.convertPropertyEntityToDTO(updatedProperty);


    }

    public void deleteProperty(Long id) {
        if (!propertyRepository.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        propertyRepository.deleteById(id);
    }
}
