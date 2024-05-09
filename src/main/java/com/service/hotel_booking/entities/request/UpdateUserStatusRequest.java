package com.service.hotel_booking.entities.request;

import com.service.hotel_booking.enumerations.UserStatus;
import lombok.Data;

@Data
public class UpdateUserStatusRequest {

    UserStatus status;

}
