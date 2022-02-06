package qadr.springboot.awsimageupload.datastore;

import org.springframework.stereotype.Repository;
import qadr.springboot.awsimageupload.profile.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeProfiles {

    private List<UserProfile> userProfileList = new ArrayList<>();

    {
        userProfileList.add(new UserProfile(UUID.fromString("6925748e-6b7c-42d9-a07a-9aeca430cacf"), "abdqadr95", null));

        userProfileList.add(new UserProfile(UUID.fromString("655e2b35-e9cc-495a-a6fa-0b4e9072b34c"), "abdqadr1888", null));

        userProfileList.add(new UserProfile(UUID.fromString("2d11e2d7-fd1b-46b2-bdc4-3bc0b08c28f3"), "abdqadr1", null));

        userProfileList.add(new UserProfile(UUID.fromString("718188c6-bc00-4777-b68e-83ce03f5ed79"), "abdqadr", null));

        userProfileList.add(new UserProfile(UUID.fromString("a38e8afc-a64a-4e85-8036-7921b5b70815"), "abdqadr18", null));
    }

    public List<UserProfile> getUserProfiles(){
        return userProfileList;
    }
    public boolean isUserExist(UUID uuid){
        for (UserProfile profile: userProfileList){
            if(profile.getUserProfileId().equals(uuid)){
                return true;
            }
        }
        return false;
    }

}
