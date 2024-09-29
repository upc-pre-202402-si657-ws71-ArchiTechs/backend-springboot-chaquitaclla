package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreatePestCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CreatePestResource;

public class CreatePestCommandFromResourceAssembler {
    public static CreatePestCommand toCommandFromResource (CreatePestResource resource){
        return new CreatePestCommand(
                resource.name(),
                resource.description(),
                resource.solution()
        );
    }
}
