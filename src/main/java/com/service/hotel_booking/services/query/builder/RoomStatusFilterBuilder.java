package com.service.hotel_booking.services.query.builder;

import com.service.hotel_booking.enumerations.RoomStatus;
import com.service.hotel_booking.services.criteria.RoomCriteria;

public class RoomStatusFilterBuilder {

    private RoomCriteria.RoomStatusFilter filter;

    private RoomStatusFilterBuilder() {
        this.filter = new RoomCriteria.RoomStatusFilter();
    }

    public static RoomStatusFilterBuilder builder() {
        return new RoomStatusFilterBuilder();
    }

    public RoomStatusFilterBuilder equals(RoomStatus value) {
        this.filter.setEquals(value);
        return this;
    }

    public RoomStatusFilterBuilder notEquals(RoomStatus value) {
        this.filter.setNotEquals(value);
        return this;
    }

    public RoomCriteria.RoomStatusFilter build() {
        return this.filter;
    }

}
