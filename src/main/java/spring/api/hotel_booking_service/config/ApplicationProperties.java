package spring.api.hotel_booking_service.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ApplicationProperties {

    CorsConfiguration cors;
    SecurityProperties security;
    CloudinaryProperties cloudinary;

    public record SecurityProperties(
            JwtProperties jwt
    ) {}

    public record JwtProperties(
            String secret,
            long accessTokenInMinutes,
            long refreshTokenInHours
    ) {}

    public record CloudinaryProperties(
            String apiKey,
            String apiSecret,
            String cloudName
    ) {}

}
