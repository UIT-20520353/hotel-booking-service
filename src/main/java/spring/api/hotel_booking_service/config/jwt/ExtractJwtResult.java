package spring.api.hotel_booking_service.config.jwt;

import io.jsonwebtoken.Claims;
import spring.api.hotel_booking_service.helper.enumeration.CheckJwtResult;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public record ExtractJwtResult(CheckJwtResult status, Claims claims) {

    public Set<String> getAuthorities() {
        if (status != CheckJwtResult.INVALID && claims.get("authorities") instanceof Collection<?> authorities) {
            return authorities.stream().map(String::valueOf).collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    public Long getUserId() {
        if (status != CheckJwtResult.INVALID) {
            return claims.get("userId", Long.class);
        }
        return null;
    }

    public String getEmail() {
        if (status != CheckJwtResult.INVALID) {
            return claims.getSubject();
        }
        return null;
    }

    public String getTokenId() {
        if (status != CheckJwtResult.INVALID) {
            return claims.getId();
        }
        return null;
    }

    public boolean isValid() {
        return CheckJwtResult.VALID == status;
    }

}

