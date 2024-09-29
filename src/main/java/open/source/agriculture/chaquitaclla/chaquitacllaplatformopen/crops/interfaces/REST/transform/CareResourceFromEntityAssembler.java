package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Care;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CareResource;

public class CareResourceFromEntityAssembler {

    public static CareResource toResourceFromEntity(Care entity) {
        return new CareResource(entity.getId(), entity.getDescription(),entity.getCareDate());
    }
}