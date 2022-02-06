package qadr.springboot.awsimageupload.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import qadr.springboot.awsimageupload.datastore.FakeProfiles;

import java.util.List;
import java.util.UUID;

@Repository
public class UserProfileRepository {
    @Autowired
    private FakeProfiles fakeProfiles;

    List<UserProfile> getFakeProfiles(){
        return fakeProfiles.getUserProfiles();
    }

    boolean isUserExist(UUID uuid){
        return fakeProfiles.isUserExist(uuid);
    }

}
