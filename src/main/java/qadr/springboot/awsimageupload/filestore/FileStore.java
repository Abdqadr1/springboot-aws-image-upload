package qadr.springboot.awsimageupload.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class FileStore {

    @Autowired
    private AmazonS3 amazonS3;

    public void save(String path, String filename, Optional<Map<String, String>> optional, InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optional.ifPresent(map -> {
            if(!map.isEmpty()) map.forEach(objectMetadata::addUserMetadata);
        });
        try{
            amazonS3.putObject(path, filename, inputStream, objectMetadata);
        } catch (AmazonServiceException e){
            throw new AmazonServiceException("Failed to store file to bucket", e);
        }
    }

    public byte[] download(String path, String key) {
        try{
            S3Object s3Object =  amazonS3.getObject(path, key);
            S3ObjectInputStream objectContent = s3Object.getObjectContent();
            return IOUtils.toByteArray(objectContent);
        } catch (AmazonServiceException | IOException e){
            throw new IllegalStateException("Failed to download file", e);
        }
    }
}
