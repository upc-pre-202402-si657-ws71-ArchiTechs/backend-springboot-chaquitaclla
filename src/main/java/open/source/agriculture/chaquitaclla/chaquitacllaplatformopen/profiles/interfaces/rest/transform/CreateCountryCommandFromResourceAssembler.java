package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.CreateCountryCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.CreateCountryResource;

public class CreateCountryCommandFromResourceAssembler {
    public static CreateCountryCommand toCommandFromResource(CreateCountryResource resource){
        return new CreateCountryCommand(resource.nameCountry());
    }
}
