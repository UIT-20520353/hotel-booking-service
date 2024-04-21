package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.repositories.UserSessionRepository;
import com.service.hotel_booking.services.UserSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserSessionServiceImpl implements UserSessionService {

    private final UserSessionRepository userSessionRepo;

    @Override
    public boolean checkUserSession(String tokenId) {
        return userSessionRepo.existsByTokenId(tokenId);
    }

    @Override
    public void removeExpiredSession(String tokenId) {
        userSessionRepo.findByTokenId(tokenId).ifPresent(userSessionRepo::delete);
    }

}
