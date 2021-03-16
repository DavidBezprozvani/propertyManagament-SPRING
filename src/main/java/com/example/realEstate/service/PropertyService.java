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

    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        Long id = propertyDTO.getId();
        Property property = dtoMapper.convertPropertyDtoToEntity(propertyDTO);
        if (id == null) {
            Owner owner = ownerRepository.getOne(propertyDTO.getOwnerId());
            property.setOwner(owner);
            Property savedProperty = propertyRepository.save(property);
            propertyDTO =  entityMapper.convertPropertyEntityToDTO(savedProperty);
        } else if (id.equals(property.getId())) {
            property = dtoMapper.convertPropertyDtoToEntity(propertyDTO);
            Property updatedProperty =  propertyRepository.save(property);
            propertyDTO = entityMapper.convertPropertyEntityToDTO(updatedProperty);
        }
        return propertyDTO;
    }

    public void deleteProperty(Long id) {
        if (!propertyRepository.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        propertyRepository.deleteById(id);
    }
}
