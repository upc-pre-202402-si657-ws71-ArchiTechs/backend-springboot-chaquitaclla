package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(SeedRolesCommand command);
}
