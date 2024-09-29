package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.application.internal.outboundservices.outboundservices.acl.ExternalProfileService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.application.internal.outboundservices.hashing.HashingService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.application.internal.outboundservices.tokens.TokenService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.aggregates.User;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.commands.SignInCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.commands.SignUpCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.services.UserCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.Optional;

/**
 * User command service implementation
 * <p>
 *     This class implements the {@link UserCommandService} interface and provides the implementation for the
 *     {@link SignInCommand} and {@link SignUpCommand} commands.
 * </p>
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {

  private final UserRepository userRepository;
  private final HashingService hashingService;
  private final TokenService tokenService;

  private final RoleRepository roleRepository;

  private final ExternalProfileService externalProfileService;

  public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService,
      TokenService tokenService, RoleRepository roleRepository, @Lazy ExternalProfileService externalProfileService) {

    this.userRepository = userRepository;
    this.hashingService = hashingService;
    this.tokenService = tokenService;
    this.roleRepository = roleRepository;
    this.externalProfileService = externalProfileService;
  }

  /**
   * Handle the sign-in command
   * <p>
   *     This method handles the {@link SignInCommand} command and returns the user and the token.
   * </p>
   * @param command the sign-in command containing the username and password
   * @return and optional containing the user matching the username and the generated token
   * @throws RuntimeException if the user is not found or the password is invalid
   */
  @Override
  public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
    var user = userRepository.findByUsername(command.username());
    if (user.isEmpty())
      throw new RuntimeException("User not found");
    if (!hashingService.matches(command.password(), user.get().getPassword()))
      throw new RuntimeException("Invalid password");

    var token = tokenService.generateToken(user.get().getUsername());
    return Optional.of(ImmutablePair.of(user.get(), token));
  }

  /**
   * Handle the sign-up command
   * <p>
   *     This method handles the {@link SignUpCommand} command and returns the user.
   * </p>
   * @param command the sign-up command containing the username and password
   * @return the created user
   */
  @Override
  public Optional<User> handle(SignUpCommand command) {
    if (userRepository.existsByUsername(command.username()))
      throw new RuntimeException("Username already exists");
    Long profileId = externalProfileService.fetchProfileIdByUsername(command.username());
    if (profileId != null && profileId > 0) {
      throw new RuntimeException("Profile already exists for this username");
    }
    var roles = command.roles().stream()
        .map(role ->
            roleRepository.findByName(role.getName())
                .orElseThrow(() -> new RuntimeException("Role name not found")))
        .toList();
    var user = new User(command.username(), hashingService.encode(command.password()), roles);
    userRepository.save(user);
    return userRepository.findByUsername(command.username());
  }
}
