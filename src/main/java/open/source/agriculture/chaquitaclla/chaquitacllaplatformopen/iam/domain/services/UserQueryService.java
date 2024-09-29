package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.aggregates.User;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.queries.GetAllUsersQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.queries.GetUserByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
  List<User> handle(GetAllUsersQuery query);
  Optional<User> handle(GetUserByIdQuery query);
  Optional<User> handle(GetUserByUsernameQuery query);
}
