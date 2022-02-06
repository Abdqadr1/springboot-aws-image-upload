package qadr.springboot.awsimageupload.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    @Query("select u from UserProfile u where u.userProfileId = ?1")
    Optional<UserProfile> findByUserProfileId(String uuid);

    /**
     * Whenever you are trying to modify a record in the Database, you have to mark it @Transactional and @Modifying,
     * which instruct Spring that it can modify existing records.
     * The modifying queries can only use void or int/Integer as return type,
     * otherwise it will throw an exception.
     * */
    @Transactional
    @Modifying
    @Query("Update UserProfile u SET u.userProfileImageLink = ?2 WHERE u.id=?1")
    void updateProfileImageLink(Long id, String filename);
}
