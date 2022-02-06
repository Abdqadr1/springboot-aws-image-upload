package qadr.springboot.awsimageupload.profile;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(
        name = "user_profiles",
        uniqueConstraints = {
                @UniqueConstraint(name = "uuid_unique", columnNames = "user_profile_id")
        }
)
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_profile_id", unique = true)
    private String userProfileId;
    @Column(name = "username")
    private String username;
    @Column(name = "user_profile_image_link")
    private String userProfileImageLink; // aws s3 link to object

    public UserProfile() {
    }

    public UserProfile(UUID userProfileId, String username, String userProfileImageLink) {
        super();
        this.userProfileId = userProfileId.toString();
        this.username = username;
        this.userProfileImageLink = userProfileImageLink;
    }

    public String getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(UUID userProfileId) {
        this.userProfileId = userProfileId.toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public Optional<String> getUserProfileImageLink() {
        return Optional.ofNullable(userProfileImageLink);
    }

    public void setUserProfileImageLink(String userProfileImageLink) {
        this.userProfileImageLink = userProfileImageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(userProfileId, that.userProfileId)
                && Objects.equals(username, that.username)
                && Objects.equals(userProfileImageLink, that.userProfileImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileId, username, userProfileImageLink);
    }
}
