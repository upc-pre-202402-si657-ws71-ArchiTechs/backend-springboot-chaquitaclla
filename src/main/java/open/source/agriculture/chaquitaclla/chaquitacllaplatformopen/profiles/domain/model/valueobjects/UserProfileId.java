package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserProfileId(Long userProfileId) {
    public UserProfileId {
        if (userProfileId < 0) {
            throw new IllegalArgumentException("UserProfile profileId cannot be negative");
        }
    }

    public UserProfileId() {
        this(0L);
    }
}
