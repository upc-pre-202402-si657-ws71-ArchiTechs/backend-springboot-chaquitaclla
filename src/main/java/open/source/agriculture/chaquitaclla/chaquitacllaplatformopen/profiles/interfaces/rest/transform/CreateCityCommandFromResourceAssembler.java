package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.CreateCityResource;

public class CreateCityCommandFromResourceAssembler {
    public static CreateCityCommand toCommandFromResource(CreateCityResource resource){
        return new CreateCityCommand(resource.nameCity(), resource.countryId());
    }
}
