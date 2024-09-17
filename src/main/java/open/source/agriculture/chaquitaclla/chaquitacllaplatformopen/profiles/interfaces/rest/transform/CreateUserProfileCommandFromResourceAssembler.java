package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.CreateUserProfileCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.CreateSubscriptionResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.CreateUserProfileResource;

public class CreateUserProfileCommandFromResourceAssembler {
    public static CreateUserProfileCommand toCommandFromResource(CreateUserProfileResource resource){
        return  new CreateUserProfileCommand(resource.firstName(), resource.lastName(), resource.email(), resource.password(), resource.cityId(), resource.subscriptionId());
    }
}
