package com.service.hotel_booking.services.criteria;

import com.service.hotel_booking.services.query.Criteria;
import com.service.hotel_booking.services.query.filter.InstantFilter;
import com.service.hotel_booking.services.query.filter.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankCriteria implements Serializable, Criteria {

    private StringFilter bankName;

    public BankCriteria(BankCriteria other) {
        this.bankName = Objects.nonNull(other.bankName) ? other.bankName : null;
    }

    @Override
    public Criteria copy() {
        return new BankCriteria(this);
    }

}
