package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.Argent;
import com.service.hotel_booking.entities.response.ArgentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ArgentMapper {

    public ArgentResponse toArgentResponse(Argent argent) {
        if (Objects.isNull(argent))
            return null;

        return new ArgentResponse(
                argent.getId(),
                argent.getIdentityNumber(),
                argent.getFrontIdentityCard(),
                argent.getBackIdentityCard(),
                argent.getSelfieImg()
        );
    }

}
