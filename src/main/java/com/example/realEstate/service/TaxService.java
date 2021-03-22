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


@Service
public class TaxService {

    private PropertyService propertyService;
    private OwnerRepository ownerRepository;

    public TaxService(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    public BigDecimal calculateYearlyTaxByPropertyId(Long id) {
        return calculateYearlyTaxByType(propertyService.getSinglePropertyById(id));
    }

    public BigDecimal calculateYearlyTaxByPropertyOwner(Long ownerId) {
        List<PropertyDTO> properties = propertyService.getAllPropertiesByOwner(ownerId);


        return BigDecimal.valueOf(properties
                .stream()
                .mapToDouble(PropertyDTO::getMarketValue)
                .sum());

    }

    public BigDecimal calculateYearlyTaxByType(PropertyDTO propertyDTO) {
        BigDecimal yearlyTax = null;

        if (propertyDTO.getType().equals(Type.HOUSE.name())) {
            yearlyTax = BigDecimal.valueOf(propertyDTO.getMarketValue() * 0.01);
        } else if (propertyDTO.getType().equals(Type.APARTMENT.name())) {
            yearlyTax = BigDecimal.valueOf(propertyDTO.getMarketValue() * 0.05);
        } else if (propertyDTO.getType().equals(Type.COMMERCIAL.name())) {
            yearlyTax = BigDecimal.valueOf(propertyDTO.getMarketValue() * 0.10);
        } else if (propertyDTO.getType().equals(Type.INDUSTRIAL.name())) {
            yearlyTax = BigDecimal.valueOf(propertyDTO.getMarketValue() * 0.20);
        }
        return BigDecimal.valueOf(Double.parseDouble(new DecimalFormat("##.##").format(yearlyTax)));

    }
}
