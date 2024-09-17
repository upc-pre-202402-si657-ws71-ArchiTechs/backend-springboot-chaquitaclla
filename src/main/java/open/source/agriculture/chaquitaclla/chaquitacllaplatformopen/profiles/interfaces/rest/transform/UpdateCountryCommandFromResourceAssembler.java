package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.UpdateCountryCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.UpdateCountryResource;

public class UpdateCountryCommandFromResourceAssembler {
    public static UpdateCountryCommand toCommandFromResource(Long countryId, UpdateCountryResource resource){
        return new UpdateCountryCommand(countryId, resource.nameCountry());
    }
}
