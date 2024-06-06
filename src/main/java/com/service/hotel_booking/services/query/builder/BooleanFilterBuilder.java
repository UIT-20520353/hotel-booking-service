package com.service.hotel_booking.services.query.builder;

import com.service.hotel_booking.services.query.filter.BooleanFilter;

public class BooleanFilterBuilder {

    private BooleanFilter filter;

    private BooleanFilterBuilder() {
        this.filter = new BooleanFilter();
    }

    public static BooleanFilterBuilder builder() {
        return new BooleanFilterBuilder();
    }

    public BooleanFilterBuilder equals(Boolean value) {
        this.filter.setEquals(value);
        return this;
    }

    public BooleanFilter build() {
        return this.filter;
    }
    
}
