package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.UpdateCityCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.UpdateCityResource;

public class UpdateCityCommandFromResourceAssembler {
    public static UpdateCityCommand toCommandFromResource(Long cityId, UpdateCityResource resource){
        return new UpdateCityCommand(cityId, resource.nameCity(), resource.countryId());
    }
}
