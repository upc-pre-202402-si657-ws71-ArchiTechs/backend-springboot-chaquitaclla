package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.aggregates.User;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.commands.SignInCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.commands.SignUpCommand;

import java.util.Optional;

public interface UserCommandService {
  Optional<ImmutablePair<User, String>> handle(SignInCommand command);
  Optional<User> handle(SignUpCommand command);
}
