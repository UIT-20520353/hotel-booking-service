package spring.api.hotel_booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import spring.api.hotel_booking_service.entity.BusinessOwner;

import java.util.Optional;

@Repository
public interface BusinessOwnerRepository extends JpaRepository<BusinessOwner, Long>, JpaSpecificationExecutor<BusinessOwner> {

    Optional<BusinessOwner> findByUserEmail(String email);
    Optional<BusinessOwner> findByUserId(Long userId);

}
