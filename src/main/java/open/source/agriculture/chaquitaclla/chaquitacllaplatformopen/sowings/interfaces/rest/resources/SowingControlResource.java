package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.SowingCondition;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.SowingSoilMoisture;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.SowingStemCondition;

import java.util.Date;

public record SowingControlResource(Long id,
                                    Long sowingId,
                                    Date controlDate,
                                    SowingCondition sowingCondition,
                                    SowingSoilMoisture sowingSoilMoisture,
                                    SowingStemCondition sowingStemCondition) {
}