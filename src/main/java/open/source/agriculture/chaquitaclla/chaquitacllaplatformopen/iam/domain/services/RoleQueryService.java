package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.entities.Role;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.queries.GetAllRolesQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
  List<Role> handle(GetAllRolesQuery query);
  Optional<Role> handle(GetRoleByNameQuery query);
}
