package com.service.hotel_booking.services.criteria;

import com.service.hotel_booking.services.query.filter.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyCriteria {

    private StringFilter name;

    public PropertyCriteria(PropertyCriteria other) {
        this.name = other.name != null ? new StringFilter(other.name) : null;
    }

}
