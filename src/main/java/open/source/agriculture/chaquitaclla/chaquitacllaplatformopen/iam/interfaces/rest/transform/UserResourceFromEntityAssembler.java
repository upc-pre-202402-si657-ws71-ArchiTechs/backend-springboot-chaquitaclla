package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.aggregates.User;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.entities.Role;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {

  public static UserResource toResourceFromEntity(User user) {
    var roles = user.getRoles().stream()
        .map(Role::getStringName)
        .toList();
    return new UserResource(user.getId(), user.getUsername(), roles);
  }
}
