package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.UpdateUserProfileResource;

public class UpdateUserProfileCommandFromResourceAssembler {
    public static UpdateUserProfileCommand toCommandFromResource(Long userProfileId, UpdateUserProfileResource resource){
        return new UpdateUserProfileCommand(userProfileId, resource.firstName(), resource.lastName(), resource.email(), resource.password(), resource.cityId(), resource.subscriptionId());
    }
}
