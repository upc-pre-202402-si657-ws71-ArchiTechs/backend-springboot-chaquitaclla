package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.aggregates.User;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {

  public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
    return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
  }
}
