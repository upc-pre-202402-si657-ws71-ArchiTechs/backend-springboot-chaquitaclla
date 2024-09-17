package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.aggregates.UserProfile;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.UserProfileResource;

public class UserProfileResourceFromEntityAssembler {
    public static UserProfileResource toResourceFromEntity(UserProfile entity){
        return new UserProfileResource(entity.getId(), entity.getName().firstName(), entity.getName().lastName(), entity.getEmail().address(), entity.getPassword().passwordUsers(),entity.getCity().getId(),entity.getSubscription().getId());
    }
}
