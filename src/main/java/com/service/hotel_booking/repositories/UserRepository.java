package com.service.hotel_booking.repositories;

import com.service.hotel_booking.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
