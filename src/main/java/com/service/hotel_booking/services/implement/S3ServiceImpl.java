package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.services.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;

    private String generateS3Key(String name, List<String> subFolders) {
        return String.join("/", subFolders) + "/" + name;
    }

    @Override
    public String generateS3Url(String key, String bucket) {
        return s3Client.utilities()
                       .getUrl(GetUrlRequest.builder().bucket(bucket).key(key).build())
                       .toString();
    }

    @Override
    public String generateS3Url(String key,List<String> subFolders, String bucket) {
        String newKey = generateS3Key(key, subFolders);
        return s3Client.utilities()
                       .getUrl(GetUrlRequest.builder().bucket(bucket).key(newKey).build())
                       .toString();
    }

    @Override
    public String uploadFile(String key, String bucket, byte[] bytes, List<String> subFolders, String mimeType) {
        String newKey = generateS3Key(key, subFolders);
        PutObjectRequest objectRequest = PutObjectRequest.builder().bucket(bucket)
                                                         .key(newKey).contentType(mimeType).acl(ObjectCannedACL.PUBLIC_READ).build();

        s3Client.putObject(objectRequest, RequestBody.fromBytes(bytes));
        return generateS3Url(newKey, bucket);
    }

}
