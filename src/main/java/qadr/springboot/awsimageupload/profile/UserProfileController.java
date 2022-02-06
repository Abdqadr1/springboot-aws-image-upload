package qadr.springboot.awsimageupload.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-profile")
@CrossOrigin("http://localhost:3000") // To accepts request from any domain or from certain domain(s)
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @GetMapping
    public List<UserProfile> getProfiles(){
        return userProfileService.getUserProfiles();
    }

    @PostMapping( path = "{userid}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, // specify that a multiple media type
            produces = MediaType.APPLICATION_JSON_VALUE // the return value
    )
    public void uploadProfileImage(@PathVariable("userid") UUID uid,
                                   @RequestParam("file") MultipartFile file){

        userProfileService.uploadProfileImage(uid, file);
    }

    @GetMapping("{userid}/image/download")
    public byte[] profileImageDownload(@PathVariable("userid") UUID uuid){
        return userProfileService.downloadProfileImage(uuid);
    }

}
