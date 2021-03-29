package com.example.realEstate.service.dao;

import java.math.BigDecimal;

public interface TaxService {

    BigDecimal calculateYearlyTaxByPropertyId(Long id);

    BigDecimal calculateYearlyTaxByPropertyOwner(Long ownerId);

    BigDecimal getMarketValueSumOfAllPropertiesByOwner(Long ownerId);
}
