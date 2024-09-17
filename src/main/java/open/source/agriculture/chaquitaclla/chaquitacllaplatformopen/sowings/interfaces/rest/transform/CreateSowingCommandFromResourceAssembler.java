package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.CreateSowingCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.CreateSowingResource;

public class CreateSowingCommandFromResourceAssembler {
    public static CreateSowingCommand fromResource(CreateSowingResource resource) {
        return new CreateSowingCommand(
                resource.cropId(),
                resource.areaLand()
        );
    }
}