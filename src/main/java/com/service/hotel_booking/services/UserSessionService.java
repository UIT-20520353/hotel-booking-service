package com.service.hotel_booking.services;

public interface UserSessionService {
    boolean checkUserSession(String tokenId);
    void removeExpiredSession(String tokenId);
}
