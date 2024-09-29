package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.SowingCondition;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.SowingSoilMoisture;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.SowingStemCondition;


public record CreateSowingControlCommand(Long sowingId,
                                         SowingCondition sowingCondition,
                                         SowingSoilMoisture sowingSoilMoisture,
                                         SowingStemCondition sowingStemCondition)
{
}