package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.commands;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String password, List<Role> roles) {
}
