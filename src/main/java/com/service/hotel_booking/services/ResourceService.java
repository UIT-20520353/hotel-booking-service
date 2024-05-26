package com.service.hotel_booking.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResourceService {

    String uploadImage(MultipartFile image);
    void checkValidImgList(List<MultipartFile> imgList);

}
