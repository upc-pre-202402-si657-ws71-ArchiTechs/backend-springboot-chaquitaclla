package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects.EmailUser;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects.PasswordUser;

public record GetUserProfileByEmailAndPasswordQuery(EmailUser emailUser, PasswordUser passwordUser) {
}
