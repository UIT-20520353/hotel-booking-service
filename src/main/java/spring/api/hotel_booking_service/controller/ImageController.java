package spring.api.hotel_booking_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import spring.api.hotel_booking_service.service.ImageService;

@Tag(name = "Image resources")
@RequestMapping("/api/image")
@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ImageController {

    ImageService imageService;

    @PostMapping(path = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(imageService.uploadImage(file));
    }

}
