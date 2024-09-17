package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.aggregates.Profile;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
  public static ProfileResource toResourceFromEntity(Profile entity) {
    return new ProfileResource(entity.getId(), entity.getEmailAddress(),
        entity.getFullName(), entity.getStreetAddress());
  }
}
