package spring.api.hotel_booking_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.api.hotel_booking_service.service.UserSessionService;

@Service
@RequiredArgsConstructor
public class UserSessionServiceImpl implements UserSessionService {

    @Override
    public boolean checkUserSession(String tokenId) {
//        return userSessionRepo.existsByTokenId(tokenId);
        return true;
    }

}
