package com.service.hotel_booking.services.criteria;

import com.service.hotel_booking.enumerations.UserRole;
import com.service.hotel_booking.enumerations.UserStatus;
import com.service.hotel_booking.services.query.Criteria;
import com.service.hotel_booking.services.query.filter.Filter;
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
public class UserCriteria implements Serializable, Criteria {

    private StringFilter name;
    private StringFilter email;
    private UserRoleFilter role;
    private UserStatusFilter status;

    public UserCriteria(UserCriteria other) {
        this.email = Objects.nonNull(other.email) ? other.email : null;
        this.name = Objects.nonNull(other.name) ? other.name : null;
        this.role = Objects.nonNull(other.role) ? other.role : null;
        this.status = Objects.nonNull(other.status) ? other.status : null;
    }

    @Override
    public Criteria copy() {
        return new UserCriteria(this);
    }

    public static class UserRoleFilter extends Filter<UserRole> implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        public UserRoleFilter() {
        }

        public UserRoleFilter(UserRoleFilter other) {
            super(other);
        }

        @Override
        public Filter<UserRole> copy() {
            return new UserRoleFilter(this);
        }
    }

    public static class UserStatusFilter extends Filter<UserStatus> implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        public UserStatusFilter() {
        }

        public UserStatusFilter(UserStatusFilter other) {
            super(other);
        }

        @Override
        public Filter<UserStatus> copy() {
            return new UserStatusFilter(this);
        }
    }

}
