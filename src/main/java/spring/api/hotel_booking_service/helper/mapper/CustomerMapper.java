package spring.api.hotel_booking_service.helper.mapper;

import org.springframework.stereotype.Component;
import spring.api.hotel_booking_service.dto.profile.CustomerDto;
import spring.api.hotel_booking_service.entity.Customer;
import spring.api.hotel_booking_service.entity.User;

@Component
public class CustomerMapper {

    public CustomerDto toCustomerDto(User user, Customer customer) {
        return new CustomerDto(
                customer.getId(),
                user.getEmail(),
                customer.getFirstName(),
                customer.getLastName(),
                user.getRole(),
                customer.getGender(),
                customer.getStatus(),
                customer.getDateOfBirth()
        );
    }

}
