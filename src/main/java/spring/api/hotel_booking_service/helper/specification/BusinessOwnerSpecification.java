package spring.api.hotel_booking_service.helper.specification;

import org.springframework.data.jpa.domain.Specification;
import spring.api.hotel_booking_service.entity.BusinessOwner;

public class BusinessOwnerSpecification {

    public static Specification<BusinessOwner> nameContains(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("firstName"), "%" + name + "%");
    }

    public static Specification<BusinessOwner> emailContains(String email) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("user").get("email"), "%" + email + "%");
    }

}
