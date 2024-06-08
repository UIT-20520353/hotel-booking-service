package com.service.hotel_booking.services.criteria;

import com.service.hotel_booking.enumerations.BookingStatus;
import com.service.hotel_booking.services.query.Criteria;
import com.service.hotel_booking.services.query.filter.Filter;
import com.service.hotel_booking.services.query.filter.InstantFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingCriteria implements Serializable, Criteria {

    private InstantFilter startDate;
    private InstantFilter endDate;
    private BookingStatusFilter status;

    public BookingCriteria(BookingCriteria other) {
        this.startDate = Objects.nonNull(other.startDate) ? other.startDate : null;
        this.endDate = Objects.nonNull(other.endDate) ? other.endDate : null;
        this.status = Objects.nonNull(other.status) ? other.status : null;
    }

    @Override
    public Criteria copy() {
        return new BookingCriteria(this);
    }

    public static class BookingStatusFilter extends Filter<BookingStatus> implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        public BookingStatusFilter() {
        }

        public BookingStatusFilter(BookingStatusFilter other) {
            super(other);
        }

        @Override
        public Filter<BookingStatus> copy() {
            return new BookingStatusFilter(this);
        }
    }

}
