import React, { useEffect, useState } from "react";
import Dropzone from "./dropzone";
export const UserProfiles = () => {
    const [profiles, setUserProfiles] = useState([]);
    const fetchUserProfiles = () => {
        fetch("http://localhost:8080/api/v1/user-profile")
            .then(res => res.json())
            .then(str => {
                console.log(str)
                setUserProfiles(str)
            })
            .catch(err => console.log(err));
    }

    useEffect(() => fetchUserProfiles(), [])

    return profiles.map((profile, index) => {
        return (
            <div key={profile.userProfileId}>
                {/* put image here */}
                {profile.userProfileId ?
                    <img alt="profile" className="profile-img"
                        src={`http://localhost:8080/api/v1/user-profile/${profile.userProfileId}/image/download`} /> :
                    null}
                <br />
                <br/>
                <h3>{profile.username}</h3>
                <p>{profile.userProfileId}</p>
                <Dropzone id={profile.userProfileId} />
                <br/>
            </div>
        )
    })

}