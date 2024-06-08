package com.service.hotel_booking.services.query.builder;

import com.service.hotel_booking.services.query.filter.LongFilter;

import java.util.List;

public class LongFilterBuilder {

    private LongFilter filter;

    private LongFilterBuilder() {
        this.filter = new LongFilter();
    }

    public static LongFilterBuilder builder() {
        return new LongFilterBuilder();
    }

    public LongFilterBuilder in(List<Long> value) {
        this.filter.setIn(value);
        return this;
    }

    public LongFilterBuilder equals(Long value) {
        this.filter.setEquals(value);
        return this;
    }

    public LongFilter build() {
        return this.filter;
    }

}
