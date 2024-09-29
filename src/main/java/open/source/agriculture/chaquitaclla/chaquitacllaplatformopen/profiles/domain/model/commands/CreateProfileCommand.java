package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands;

public record CreateProfileCommand(String firstName, String lastName, String username, String email,
                                   String street, String number, String city,
                                   String postalCode, String country) {
}