package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.UpdateSowingControlCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.UpdateSowingControlResource;

public class UpdateSowingControlCommandFromResourceAssembler {
    public static UpdateSowingControlCommand fromResource(UpdateSowingControlResource resource,Long sowingId, Long sowingControlId ) {
        return new UpdateSowingControlCommand(
                sowingId,
                sowingControlId,
                resource.sowingCondition(),
                resource.sowingSoilMoisture(),
                resource.sowingStemCondition()
        );
    }
}