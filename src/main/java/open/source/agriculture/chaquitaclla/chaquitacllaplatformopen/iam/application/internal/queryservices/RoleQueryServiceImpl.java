package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.application.internal.queryservices;

import org.springframework.stereotype.Service;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.entities.Role;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.queries.GetAllRolesQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.queries.GetRoleByNameQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.services.RoleQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.infrastructure.persistence.jpa.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

/**
 * RoleQueryServiceImpl class
 * This class is used to handle the role queries
 */
@Service
public class RoleQueryServiceImpl implements RoleQueryService {
  private final RoleRepository roleRepository;

  /**
   * RoleQueryServiceImpl constructor
   * @param roleRepository the role repository
   */
  public RoleQueryServiceImpl(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  /**
   * Handle the get all roles query
   * @param query the get all roles query
   * @return List<Role> the list of roles
   */
  @Override
  public List<Role> handle(GetAllRolesQuery query) {
    return roleRepository.findAll();
  }

  /**
   * Handle the get role by name query
   * @param query the get role by name query
   * @return Optional<Role> the role
   */
  @Override
  public Optional<Role> handle(GetRoleByNameQuery query) {
    return roleRepository.findByName(query.name());
  }
}
