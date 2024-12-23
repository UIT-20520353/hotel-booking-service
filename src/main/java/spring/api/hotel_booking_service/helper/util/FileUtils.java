package spring.api.hotel_booking_service.helper.util;

import org.springframework.web.multipart.MultipartFile;
import spring.api.hotel_booking_service.helper.exception.BadRequestException;

import java.util.Arrays;
import java.util.List;

import static spring.api.hotel_booking_service.helper.constant.Message.FILE_TYPE_ERROR;

public class FileUtils {

    // Allowed file types
    private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList(
            "image/jpeg",
            "image/png",
            "application/pdf"
    );

    // Maximum file size in bytes (e.g., 5 MB)
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    /**
     * Checks if the file size is within the allowed limit.
     *
     * @param file the uploaded file
     * @return true if file size is valid, false otherwise
     */
    public static boolean isFileSizeValid(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return false;
        }
        return file.getSize() <= MAX_FILE_SIZE;
    }

    /**
     * Checks if the file type is allowed.
     *
     * @param file the uploaded file
     * @return true if file type is valid, false otherwise
     */
    public static boolean isFileTypeValid(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return false;
        }
        try {
            String mimeType = file.getContentType();
            return ALLOWED_MIME_TYPES.contains(mimeType);
        } catch (Exception e) {
            throw new BadRequestException(FILE_TYPE_ERROR);
        }
    }

    /**
     * Validates both file size and file type.
     *
     * @param file the uploaded file
     * @return true if both validations pass, false otherwise
     */
    public static boolean isValidFile(MultipartFile file) {
        return isFileSizeValid(file) && isFileTypeValid(file);
    }

}
