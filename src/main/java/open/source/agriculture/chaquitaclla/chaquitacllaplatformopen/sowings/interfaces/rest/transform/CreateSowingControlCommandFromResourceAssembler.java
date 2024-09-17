package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.CreateSowingControlCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.CreateSowingControlResource;

public class CreateSowingControlCommandFromResourceAssembler {
    public static CreateSowingControlCommand fromResource(Long sowingId, CreateSowingControlResource resource) {
        return new CreateSowingControlCommand(
                sowingId,
                resource.sowingCondition(),
                resource.sowingSoilMoisture(),
                resource.sowingStemCondition()
        );
    }
}