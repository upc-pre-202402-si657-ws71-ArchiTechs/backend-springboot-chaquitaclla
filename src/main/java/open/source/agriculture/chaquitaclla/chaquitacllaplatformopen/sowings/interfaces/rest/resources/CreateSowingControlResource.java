package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.SowingCondition;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.SowingSoilMoisture;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.SowingStemCondition;

public record CreateSowingControlResource(SowingCondition sowingCondition,
                                          SowingSoilMoisture sowingSoilMoisture,
                                          SowingStemCondition sowingStemCondition) {
}