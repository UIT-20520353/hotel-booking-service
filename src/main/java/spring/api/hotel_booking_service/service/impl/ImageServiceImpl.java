package spring.api.hotel_booking_service.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.api.hotel_booking_service.helper.exception.BadRequestException;
import spring.api.hotel_booking_service.service.ImageService;

import java.util.Map;

import static spring.api.hotel_booking_service.helper.constant.Message.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file) {
        System.out.println(file.getContentType());
        if (file.isEmpty()) {
            throw new BadRequestException(IMAGE_REQUIRED_ERROR);
        }

        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new BadRequestException(IMAGE_UPLOAD_FAIL_ERROR);
        }
    }

}
