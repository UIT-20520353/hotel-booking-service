package com.service.hotel_booking.services.criteria;

import com.service.hotel_booking.enumerations.AmenityType;
import com.service.hotel_booking.services.query.Criteria;
import com.service.hotel_booking.services.query.filter.Filter;
import com.service.hotel_booking.services.query.filter.LongFilter;
import com.service.hotel_booking.services.query.filter.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmenityCriteria implements Serializable, Criteria {

    private LongFilter id;
    private StringFilter name;
    private AmenityTypeFilter type;

    public AmenityCriteria(AmenityCriteria other) {
        this.id = Objects.nonNull(other.id) ? other.id : null;
        this.name = Objects.nonNull(other.name) ? other.name : null;
        this.type = Objects.nonNull(other.type) ? other.type : null;
    }

    @Override
    public Criteria copy() {
        return new AmenityCriteria(this);
    }

    public static class AmenityTypeFilter extends Filter<AmenityType> implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        public AmenityTypeFilter() {
        }

        public AmenityTypeFilter(AmenityTypeFilter other) {
            super(other);
        }

        @Override
        public Filter<AmenityType> copy() {
            return new AmenityTypeFilter(this);
        }
    }

}
