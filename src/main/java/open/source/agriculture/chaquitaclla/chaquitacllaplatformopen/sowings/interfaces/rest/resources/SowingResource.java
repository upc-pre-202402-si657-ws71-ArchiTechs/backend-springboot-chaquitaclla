package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.valueobjects.DateRange;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.CropId;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.PhenologicalPhase;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.ProfileId;

import java.util.List;
import java.util.Set;


public record SowingResource(Long id, DateRange dateRange, Integer profileId, Integer areaLand, boolean status, Integer cropId, Integer phenologicalPhase) {
}