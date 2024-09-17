package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.valueobjects.DateRange;

public record UpdateSowingCommand(Long Id, Integer cropId, Integer areaLand) {
}