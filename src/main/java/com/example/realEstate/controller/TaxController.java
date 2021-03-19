package com.example.realEstate.controller;

import com.example.realEstate.service.TaxService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/tax")
public class TaxController {

    private TaxService taxService;

    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    @GetMapping("/{id}")
    public BigDecimal getYearlyTaxByPropertyId(@PathVariable Long id) {
        return taxService.calculateYearlyTaxByPropertyId(id);
    }
}
