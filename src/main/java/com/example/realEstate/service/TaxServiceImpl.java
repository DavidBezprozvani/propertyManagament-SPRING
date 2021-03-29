package com.example.realEstate.service;

import com.example.realEstate.entity.Type;
import com.example.realEstate.entity.dto.PropertyDTO;
import com.example.realEstate.service.dao.TaxService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;


@Service
public class TaxServiceImpl implements TaxService {

    private PropertyServiceImpl propertyServiceImpl;


    public TaxServiceImpl(PropertyServiceImpl propertyServiceImpl) {
        this.propertyServiceImpl = propertyServiceImpl;
    }


    @Override
    public BigDecimal calculateYearlyTaxByPropertyId(Long id) {
        return calculateYearlyTaxByType(propertyServiceImpl.getSinglePropertyById(id));
    }

    @Override
    public BigDecimal calculateYearlyTaxByPropertyOwner(Long ownerId) {
        List<PropertyDTO> properties = propertyServiceImpl.getAllPropertiesByOwner(ownerId);
        return properties
                .stream()
                .map(this::calculateYearlyTaxByType)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal getMarketValueSumOfAllPropertiesByOwner(Long ownerId) {
        List<PropertyDTO> properties = propertyServiceImpl.getAllPropertiesByOwner(ownerId);
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
