package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.entities.SowingControl;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.SowingControlResource;

public class SowingControlResourceFromEntityAssembler {
    public static SowingControlResource toResourceFromEntity(SowingControl entity) {
        return new SowingControlResource(
                entity.getId(),
                entity.getSowing().getId(),
                entity.getControlDate(),
                entity.getSowingCondition(),
                entity.getSowingSoilMoisture(),
                entity.getSowingStemCondition());
    }
}