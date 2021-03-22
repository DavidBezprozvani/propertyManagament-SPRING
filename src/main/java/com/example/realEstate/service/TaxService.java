package com.example.realEstate.service;

import com.example.realEstate.entity.Owner;
import com.example.realEstate.entity.Property;
import com.example.realEstate.entity.Type;
import com.example.realEstate.entity.dto.PropertyDTO;
import com.example.realEstate.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaxService {

    private PropertyService propertyService;


    public TaxService(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    public BigDecimal calculateYearlyTaxByPropertyId(Long id) {
        return calculateYearlyTaxByType(propertyService.getSinglePropertyById(id));
    }

    public BigDecimal calculateYearlyTaxByPropertyOwner(Long ownerId) {
        List<PropertyDTO> properties = propertyService.getAllPropertiesByOwner(ownerId);
        return properties
                .stream()
                .map(this::calculateYearlyTaxByType)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getMarketValueSumOfAllPropertiesByOwner(Long ownerId) {
        List<PropertyDTO> properties = propertyService.getAllPropertiesByOwner(ownerId);
                return properties
                .stream()
                .map(PropertyDTO::getMarketValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public BigDecimal calculateYearlyTaxByType(PropertyDTO propertyDTO) {
        BigDecimal yearlyTax = null;

        if (propertyDTO.getType().equals(Type.HOUSE.name())) {
            yearlyTax = propertyDTO.getMarketValue().multiply(new BigDecimal("0.01"));
        } else if (propertyDTO.getType().equals(Type.APARTMENT.name())) {
            yearlyTax = propertyDTO.getMarketValue().multiply(new BigDecimal("0.05"));
        } else if (propertyDTO.getType().equals(Type.COMMERCIAL.name())) {
            yearlyTax = propertyDTO.getMarketValue().multiply(new BigDecimal("0.10"));
        } else if (propertyDTO.getType().equals(Type.INDUSTRIAL.name())) {
            yearlyTax = propertyDTO.getMarketValue().multiply(new BigDecimal("0.20"));
        }
        return BigDecimal.valueOf(Double.parseDouble(new DecimalFormat("##.##").format(yearlyTax)));

    }
}
