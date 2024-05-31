package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.Argent;
import com.service.hotel_booking.entities.response.ArgentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ArgentMapper {

    public ArgentDto toArgentResponse(Argent argent) {
        if (Objects.isNull(argent))
            return null;

        return new ArgentDto(
                argent.getId(),
                argent.getIdentityNumber(),
                argent.getFrontIdentityCard(),
                argent.getBackIdentityCard(),
                argent.getSelfieImg()
        );
    }

}
