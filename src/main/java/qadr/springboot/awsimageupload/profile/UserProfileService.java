package qadr.springboot.awsimageupload.profile;

import com.amazonaws.services.autoscalingplans.model.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import qadr.springboot.awsimageupload.bucket.BucketName;
import qadr.springboot.awsimageupload.filestore.FileStore;

import java.io.IOException;
import java.util.*;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private FileStore fileStore;

    List<UserProfile> getUserProfiles (){
        return userProfileRepository.findAll();
    }

    void uploadProfileImage(UUID uid, MultipartFile file) {
        // 1. check image is not empty
        isFileEmpty(file);
        // 2. check if file is an image
        isImage(file);
        // 3. check whether user exist in the db
        UserProfile user = getUserOrThrow(uid);
        // 4. Grab some metadata from file if any
        Map<String, String> metadata = extractMetadata(file);
        // 5. Store the image in aws s3 and update db with image link
        // Each user has a folder for every of the user upload
        String path = BucketName.AWS_PROFILE_IMAGE.getBucketName() + "/"+user.getUserProfileId();
        String filename = file.getOriginalFilename()
                .substring(0, file.getOriginalFilename().lastIndexOf("."))+ "-"+UUID.randomUUID();
        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            userProfileRepository.updateProfileImageLink(user.getId(), filename);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("content-type", file.getContentType());
        metadata.put("content-length", String.valueOf(file.getSize()));
        return metadata;
    }

    private UserProfile getUserOrThrow(UUID uid) {
        UserProfile user = userProfileRepository.findByUserProfileId(uid.toString())
                .orElseThrow(() -> new ObjectNotFoundException(String.format("User profile %s not found", uid)));
        return user;
    }

    private void isImage(MultipartFile file) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
        System.err.println(extension);
        if(!Arrays.asList("png", "jpg", "jpeg", "gif").contains(extension)){
            throw new IllegalStateException("File is not an image file");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if(file.isEmpty()) throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
    }


    public byte[] downloadProfileImage(UUID uuid) {
        UserProfile user = getUserOrThrow(uuid);
        String path = String.format("%s/%s",
                BucketName.AWS_PROFILE_IMAGE.getBucketName(),
                user.getUserProfileId());
        return user.getUserProfileImageLink()
                .map(link -> fileStore.download(path, link))
                .orElse(new byte[0]);
    }
}
