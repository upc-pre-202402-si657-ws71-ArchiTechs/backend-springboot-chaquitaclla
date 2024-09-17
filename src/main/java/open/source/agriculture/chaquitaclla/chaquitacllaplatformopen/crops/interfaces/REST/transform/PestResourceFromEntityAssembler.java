package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Pest;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.PestResource;

public class PestResourceFromEntityAssembler {
    public static PestResource toResourceFromEntity(Pest Entity){
        return new PestResource(
                Entity.getId(),
                Entity.getName(),
                Entity.getDescription(),
                Entity.getSolution()
        );
    }
}
