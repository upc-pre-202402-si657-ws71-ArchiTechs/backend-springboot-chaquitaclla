package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.acl;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.CreateUserProfileCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetUserProfileByEmailQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects.EmailUser;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.services.UserProfileCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.services.UserProfileQueryService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileContextFacade {
    private final UserProfileCommandService userProfileCommandService;
    private final UserProfileQueryService userProfileQueryService;

    public UserProfileContextFacade(UserProfileCommandService userProfileCommandService, UserProfileQueryService userProfileQueryService) {
        this.userProfileCommandService = userProfileCommandService;
        this.userProfileQueryService = userProfileQueryService;
    }
    public Long createUserProfile(String firstName, String lastName, String emailUser, String passwordUser,Long cityId,Long subscriptionId) {
        var createUserProfileCommand = new CreateUserProfileCommand(firstName, lastName, emailUser, passwordUser, cityId, subscriptionId);
        var userProfile = userProfileCommandService.handle(createUserProfileCommand);
        if (userProfile.isEmpty())
            return 0L;
        return userProfile.get().getId();
    }
    public Long fetchUserProfileIdByEmail(String email) {
        var getUserProfileByEmailQuery = new GetUserProfileByEmailQuery(new EmailUser(email));
        var userProfile = userProfileQueryService.handle(getUserProfileByEmailQuery);
        if(userProfile.isEmpty())
            return 0L;
        return userProfile.get().getId();
    }
}
