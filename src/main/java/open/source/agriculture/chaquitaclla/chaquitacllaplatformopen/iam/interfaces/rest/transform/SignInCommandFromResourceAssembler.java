package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.commands.SignInCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {

  public static SignInCommand toCommandFromResource(SignInResource signInResource) {
    return new SignInCommand(signInResource.username(), signInResource.password());
  }
}
