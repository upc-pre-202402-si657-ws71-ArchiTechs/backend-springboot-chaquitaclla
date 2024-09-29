package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateCareCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CreateCareResource;

public class CreateCareCommandFromResourceAssembler {

    public static CreateCareCommand toCommandFromResource(CreateCareResource resource) {
        return new CreateCareCommand(
                resource.description(),
                resource.careDate()
        );
    }
}