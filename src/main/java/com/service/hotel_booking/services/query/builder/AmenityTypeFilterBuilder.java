package com.service.hotel_booking.services.query.builder;

import com.service.hotel_booking.enumerations.AmenityType;
import com.service.hotel_booking.services.criteria.AmenityCriteria;

public class AmenityTypeFilterBuilder {

    private AmenityCriteria.AmenityTypeFilter filter;

    private AmenityTypeFilterBuilder() {
        this.filter = new AmenityCriteria.AmenityTypeFilter();
    }

    public static AmenityTypeFilterBuilder builder() {
        return new AmenityTypeFilterBuilder();
    }

    public AmenityTypeFilterBuilder equals(AmenityType value) {
        this.filter.setEquals(value);
        return this;
    }

    public AmenityCriteria.AmenityTypeFilter build() {
        return this.filter;
    }
    
}
