package com.service.hotel_booking.services.criteria;

import com.service.hotel_booking.enumerations.PropertyStatus;
import com.service.hotel_booking.enumerations.UserRole;
import com.service.hotel_booking.services.query.Criteria;
import com.service.hotel_booking.services.query.filter.Filter;
import com.service.hotel_booking.services.query.filter.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyCriteria implements Serializable, Criteria {

    private StringFilter name;
    private PropertyStatusFilter status;

    public PropertyCriteria(PropertyCriteria other) {
        this.name = other.name != null ? new StringFilter(other.name) : null;
        this.status = other.status != null ? new PropertyStatusFilter(other.status) : null;
    }

    @Override
    public Criteria copy() {
        return new PropertyCriteria(this);
    }

    public static class PropertyStatusFilter extends Filter<PropertyStatus> implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        public PropertyStatusFilter() {
        }

        public PropertyStatusFilter(PropertyStatusFilter other) {
            super(other);
        }

        @Override
        public Filter<PropertyStatus> copy() {
            return new PropertyStatusFilter(this);
        }
    }

}
