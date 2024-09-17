package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(Long id, String username, List<String> roles) {
}
