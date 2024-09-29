package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.application.internal.outboundservices.outboundservices.acl;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ExtProfileService {
    private final ProfilesContextFacade profilesContextFacade;

    public ExtProfileService(@Lazy ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public Long fetchProfileIdByUsername(String username) {
        var profileId = profilesContextFacade.fetchProfileIdByUsername(username);
        return profileId;
    }
}