package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands;

public record CreateUserProfileCommand(String firstName,String lastName,String email,String password,Long cityId,Long subscriptionId) {
}
