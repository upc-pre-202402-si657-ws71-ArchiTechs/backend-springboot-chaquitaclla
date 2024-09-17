package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources;

public record CreateUserProfileResource(String firstName,String lastName,String email,String password,Long cityId,Long subscriptionId) {
}
