package com.service.hotel_booking.services;

import org.springframework.web.multipart.MultipartFile;

public interface ResourceService {

    String uploadImage(MultipartFile image);

}
