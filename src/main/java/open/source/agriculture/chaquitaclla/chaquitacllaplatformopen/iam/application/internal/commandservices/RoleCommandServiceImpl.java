package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.application.internal.commandservices;

import org.springframework.stereotype.Service;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.commands.SeedRolesCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.entities.Role;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.valueobjects.Roles;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.services.RoleCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.infrastructure.persistence.jpa.repositories.RoleRepository;

import java.util.Arrays;

/**
 * Implementation of {@link RoleCommandService} to handle {@link SeedRolesCommand}
 */
@Service
public class RoleCommandServiceImpl implements RoleCommandService {

  private final RoleRepository roleRepository;

  public RoleCommandServiceImpl(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  /**
   * This method will handle the {@link SeedRolesCommand} and will create the roles if not exists
   * @param command {@link SeedRolesCommand}
   * @see SeedRolesCommand
   */
  @Override
  public void handle(SeedRolesCommand command) {
    Arrays.stream(Roles.values())
        .forEach(role -> {
          if(!roleRepository.existsByName(role)) {
            roleRepository.save(new Role(Roles.valueOf(role.name())));
          }
        } );
  }
}
