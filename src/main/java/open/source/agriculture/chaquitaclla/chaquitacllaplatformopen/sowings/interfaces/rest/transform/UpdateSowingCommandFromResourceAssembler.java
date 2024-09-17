package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.UpdateSowingCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.UpdateSowingResource;

public class UpdateSowingCommandFromResourceAssembler {
    public static UpdateSowingCommand fromResource(Long sowingId, UpdateSowingResource resource) {
        return new UpdateSowingCommand(
                sowingId,
                resource.cropId(),
                resource.areaLand()
        );
    }
}