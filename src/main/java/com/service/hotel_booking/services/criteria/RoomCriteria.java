package com.service.hotel_booking.services.criteria;

import com.service.hotel_booking.enumerations.RoomStatus;
import com.service.hotel_booking.services.query.Criteria;
import com.service.hotel_booking.services.query.filter.Filter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomCriteria implements Serializable, Criteria {

    private RoomStatusFilter status;

    public RoomCriteria(RoomCriteria other) {
        this.status = other.status != null ? other.status : null;
    }

    @Override
    public Criteria copy() {
        return new RoomCriteria(this);
    }

    public static class RoomStatusFilter extends Filter<RoomStatus> implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        public RoomStatusFilter() {
        }

        public RoomStatusFilter(RoomStatusFilter other) {
            super(other);
        }

        @Override
        public Filter<RoomStatus> copy() {
            return new RoomStatusFilter(this);
        }
    }

}
