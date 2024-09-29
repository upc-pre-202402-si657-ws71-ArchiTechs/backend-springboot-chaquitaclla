package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.SowingCondition;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.SowingSoilMoisture;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.SowingStemCondition;

public record UpdateSowingControlResource(SowingCondition sowingCondition,
                                          SowingSoilMoisture sowingSoilMoisture,
                                          SowingStemCondition sowingStemCondition) {
}
