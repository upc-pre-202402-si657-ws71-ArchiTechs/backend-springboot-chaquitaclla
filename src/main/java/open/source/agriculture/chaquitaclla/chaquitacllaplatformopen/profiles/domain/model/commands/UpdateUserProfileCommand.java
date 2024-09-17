package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands;

public record UpdateUserProfileCommand(Long id,String firstName,String lastName,String email,String password,Long cityId,Long subscriptionId) {
}
