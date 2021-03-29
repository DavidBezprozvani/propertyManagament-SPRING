package com.example.realEstate.controller;

import com.example.realEstate.service.TaxServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/tax")
public class TaxController {

    private TaxServiceImpl taxServiceImpl;

    public TaxController(TaxServiceImpl taxServiceImpl) {
        this.taxServiceImpl = taxServiceImpl;
    }

    @GetMapping("/{id}")
    public BigDecimal getYearlyTaxByPropertyId(@PathVariable Long id) {
        return taxServiceImpl.calculateYearlyTaxByPropertyId(id);
    }

    @GetMapping("/owner/{ownerId}")
    public BigDecimal getTotalYearlyTaxByOwnerId(@PathVariable Long ownerId) {
        return taxServiceImpl.calculateYearlyTaxByPropertyOwner(ownerId);
    }

    @GetMapping("/owner/{ownerId}/properties")
    public BigDecimal getMarketValueSumOfAllPropertiesByOwner(@PathVariable Long ownerId) {
        return taxServiceImpl.getMarketValueSumOfAllPropertiesByOwner(ownerId);
    }
}
