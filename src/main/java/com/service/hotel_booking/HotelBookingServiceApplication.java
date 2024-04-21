package com.service.hotel_booking;

import com.service.hotel_booking.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ ApplicationProperties.class})
public class HotelBookingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelBookingServiceApplication.class, args);
    }

}
