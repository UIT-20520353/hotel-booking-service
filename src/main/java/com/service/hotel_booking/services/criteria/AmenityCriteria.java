package com.service.hotel_booking.services.criteria;

import com.service.hotel_booking.enumerations.AmenityType;
import com.service.hotel_booking.services.query.Criteria;
import com.service.hotel_booking.services.query.filter.BooleanFilter;
import com.service.hotel_booking.services.query.filter.Filter;
import com.service.hotel_booking.services.query.filter.LongFilter;
import com.service.hotel_booking.services.query.filter.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AmenityCriteria implements Serializable, Criteria {

    private LongFilter id;
    private StringFilter name;
    private AmenityTypeFilter type;
    private BooleanFilter isDeleted;

    public AmenityCriteria(AmenityCriteria other) {
        this.id = Objects.nonNull(other.id) ? other.id : null;
        this.name = Objects.nonNull(other.name) ? other.name : null;
        this.type = Objects.nonNull(other.type) ? other.type : null;
        this.isDeleted = Objects.nonNull(other.isDeleted) ? other.isDeleted : null;
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

    public static class Builder {
        private LongFilter id;
        private StringFilter name;
        private AmenityTypeFilter type;
        private BooleanFilter isDeleted;

        Builder() {}

        public AmenityCriteria.Builder id(LongFilter id) {
            this.id = id;
            return this;
        }

        public AmenityCriteria.Builder name(StringFilter name) {
            this.name = name;
            return this;
        }

        public AmenityCriteria.Builder type(AmenityTypeFilter type) {
            this.type = type;
            return this;
        }

        public AmenityCriteria.Builder isDeleted(BooleanFilter isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        public AmenityCriteria build() {
            return new AmenityCriteria(this.id, this.name, this.type, this.isDeleted);
        }

        public String toString() {
            return "AmenityCriteria.Builder(id=" + this.id + ", name=" + this.name + ", type=" + this.type + ")";
        }
    }

}
