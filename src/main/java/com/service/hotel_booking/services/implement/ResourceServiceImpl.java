package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.services.ResourceService;
import com.service.hotel_booking.services.S3Service;
import com.service.hotel_booking.utils.FileUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResourceServiceImpl implements ResourceService {

    S3Service s3Service;

    @Override
    public String uploadImage(MultipartFile image) {
        byte[] bytes = FileUtils.checkFile(image);
        String key = "image" + "-" + UUID.randomUUID() + ".png";

        return s3Service.uploadFile(key, "hotel-booking-storage-30-04-2024", bytes, List.of("image"), image.getContentType());
    }

    @Override
    public void checkValidImgList(List<MultipartFile> imgList) {
        imgList.forEach(img -> {
            FileUtils.checkFile(img);
            System.out.println("Valid: " + img.getOriginalFilename());
        });
    }

    @Override
    public String uploadPropertyImage(byte[] bytes, String contentType) {
        String key = "property-image" + "-" + UUID.randomUUID() + ".png";
        return s3Service.uploadFile(key, "hotel-booking-storage-30-04-2024", bytes, List.of("image", "property"), contentType);
    }

    @Override
    public String uploadRoomImage(byte[] bytes, String contentType) {
        String key = "room-image" + "-" + UUID.randomUUID() + ".png";
        return s3Service.uploadFile(key, "hotel-booking-storage-30-04-2024", bytes, List.of("image", "room"), contentType);
    }

    @Override
    public String uploadBankQr(MultipartFile qrCode) {
        byte[] bytes = FileUtils.checkFile(qrCode);
        String key = "bank-qr-" + UUID.randomUUID() + ".png";
        return s3Service.uploadFile(key, "hotel-booking-storage-30-04-2024", bytes, List.of("image", "bank"), qrCode.getContentType());
    }

}
