package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.entities.User;
import com.service.hotel_booking.entities.User_;
import com.service.hotel_booking.entities.request.UpdateUserStatusRequest;
import com.service.hotel_booking.entities.response.UserDetailDto;
import com.service.hotel_booking.entities.response.UserProfileResponse;
import com.service.hotel_booking.enumerations.UserRole;
import com.service.hotel_booking.exceptions.BadRequestException;
import com.service.hotel_booking.mappers.UserMapper;
import com.service.hotel_booking.repositories.UserRepository;
import com.service.hotel_booking.services.UserService;
import com.service.hotel_booking.services.criteria.UserCriteria;
import com.service.hotel_booking.services.query.QueryService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.service.hotel_booking.constant.MessageConstant.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl extends QueryService<User> implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public Page<UserDetailDto> getAllUsers(UserCriteria criteria, Pageable pageable) {
        Specification<User> specification = createSpecification(criteria);
        return userRepository.findAll(specification, pageable).map(userMapper::toUserDetail);
    }

    private Specification<User> createSpecification(UserCriteria criteria) {
        Specification<User> specification = Specification.where(null);
        if (criteria != null) {
            if (Objects.nonNull(criteria.getEmail())) {
                specification = specification.and(buildSpecification(criteria.getEmail(),
                                                                     root -> root.get(User_.email)));
            }

            if (Objects.nonNull(criteria.getName())) {
                Specification<User> specificationCustom = buildSpecification(criteria.getName(),
                                                                             root -> root.get(User_.firstName));
                specificationCustom = specificationCustom.or(buildSpecification(criteria.getName(),
                                                                                root -> root.get(User_.lastName)));
                specification = specification.and(specificationCustom);
            }

            if (Objects.nonNull(criteria.getRole())) {
                specification = specification.and(buildSpecification(criteria.getRole(), User_.role));
            }

            if (Objects.nonNull(criteria.getStatus())) {
                specification = specification.and(buildSpecification(criteria.getStatus(), User_.status));
            }
        }

        return specification;
    }

    @Override
    public UserProfileResponse getUserProfile(Long id) {
        User user = userRepository
                        .findById(id)
                        .orElseThrow(() -> new BadRequestException(USER_NOT_EXIST));

        return userMapper.toUserProfile(user);
    }

    @Override
    public UserDetailDto getUserDetail(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException(USER_NOT_EXIST));

        return userMapper.toUserDetail(user);
    }

    @Override
    @Transactional
    public void updateUserStatus(Long id, UpdateUserStatusRequest body) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException(USER_NOT_EXIST));

        if (UserRole.USER.equals(user.getRole()) || UserRole.ADMIN.equals(user.getRole())) {
            throw new BadRequestException(USER_IS_NOT_ARGENT);
        }

        switch (user.getStatus()) {
            case REJECTED -> throw new BadRequestException(ARGENT_ALREADY_REJECTED);
            case ACTIVE -> throw new BadRequestException(ARGENT_ALREADY_ACTIVE);
            case BLOCKED -> throw new BadRequestException(ARGENT_ALREADY_BLOCKED);
            default -> user.setStatus(body.getStatus());
        }
    }

}
