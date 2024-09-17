package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.aggregates.UserProfile;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.CreateUserProfileCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.DeleteUserProfileCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.UpdateUserProfileCommand;

import java.util.Optional;

public interface UserProfileCommandService {
    Optional<UserProfile> handle(CreateUserProfileCommand command);
    Optional<UserProfile> handle(UpdateUserProfileCommand command);
    void handle(DeleteUserProfileCommand command);
}
