package spring.api.hotel_booking_service.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    String uploadImage(MultipartFile file);
    List<String> uploadImages(List<MultipartFile> files);

}
