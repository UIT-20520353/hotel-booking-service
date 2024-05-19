package com.service.hotel_booking.services.criteria;

import com.service.hotel_booking.services.query.Criteria;
import com.service.hotel_booking.services.query.filter.LongFilter;
import com.service.hotel_booking.services.query.filter.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelServiceCriteria implements Serializable, Criteria {

    private LongFilter id;
    private StringFilter name;

    public HotelServiceCriteria(HotelServiceCriteria other) {
        this.id = Objects.nonNull(other.id) ? other.id : null;
        this.name = Objects.nonNull(other.name) ? other.name : null;
    }

    @Override
    public Criteria copy() {
        return new HotelServiceCriteria(this);
    }

}
