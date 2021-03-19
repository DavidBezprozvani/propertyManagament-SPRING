package com.example.realEstate.service;

import com.example.realEstate.entity.Type;
import com.example.realEstate.entity.dto.PropertyDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

@Service
public class TaxService {

    private PropertyService propertyService;

    public TaxService(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    public BigDecimal calculateYearlyTaxByPropertyId(Long id) {
        return calculateYearlyTaxByType(propertyService.getSinglePropertyById(id));
    }


    public BigDecimal calculateYearlyTaxByType(PropertyDTO propertyDTO) {
        BigDecimal yearlyTax = null;

        if (propertyDTO.getType().equals(Type.HOUSE.name())) {
            yearlyTax = BigDecimal.valueOf(propertyDTO.getMarketValue() * 0.01);
        }
        if (propertyDTO.getType().equals(Type.APARTMENT.name())) {
            yearlyTax = BigDecimal.valueOf(propertyDTO.getMarketValue() * 0.05);
        }
        if (propertyDTO.getType().equals(Type.COMMERCIAL.name())) {
            yearlyTax = BigDecimal.valueOf(propertyDTO.getMarketValue() * 0.10);
        }
        if (propertyDTO.getType().equals(Type.INDUSTRIAL.name())) {
            yearlyTax = BigDecimal.valueOf(propertyDTO.getMarketValue() * 0.20);
        }
        return BigDecimal.valueOf(Double.parseDouble(new DecimalFormat("##.##").format(yearlyTax)));
    }
}
