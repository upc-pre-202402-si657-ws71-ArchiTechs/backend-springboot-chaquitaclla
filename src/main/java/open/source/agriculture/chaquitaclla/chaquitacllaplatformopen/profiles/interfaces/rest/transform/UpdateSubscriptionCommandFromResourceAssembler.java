package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.UpdateSubscriptionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.UpdateSubscriptionResource;

public class UpdateSubscriptionCommandFromResourceAssembler {
    public static UpdateSubscriptionCommand toCommandFromResource(Long subscriptionId, UpdateSubscriptionResource resource){
        return new UpdateSubscriptionCommand(subscriptionId, resource.nameSubscription(), resource.description(), resource.price());
    }
}
