package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.application.internal.outboundservices.acl.ExternalIamService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.aggregates.Profile;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.CreateProfileCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects.EmailAddress;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.services.ProfileCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
  private final ProfileRepository profileRepository;
  private final ExternalIamService externalIamService;

  public ProfileCommandServiceImpl(ProfileRepository profileRepository, @Lazy ExternalIamService externalIamService) {
    this.profileRepository = profileRepository;
    this.externalIamService = externalIamService;
  }

  @Override
  public Optional<Profile> handle(CreateProfileCommand command) {
    Long existingUserId = externalIamService.fetchUserIdByUsername(command.username());
    if (existingUserId == null || existingUserId == 0) {
      throw new IllegalArgumentException("User with username " + command.username() + " doesn't exist");
    }

    var emailAddress = new EmailAddress(command.email());
    profileRepository.findByEmail(emailAddress).ifPresent(profile -> {
      throw new IllegalArgumentException("Profile with email " + command.email() + " already exists");
    });

    var profile = new Profile(command);
    profileRepository.save(profile);
    System.out.println("Profile created and saved for username: " + command.username());

    return Optional.of(profile);
  }
}