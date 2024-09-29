package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.valueobjects.DateRange;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.CropId;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.ProfileId;

public record CreateSowingResource(Integer cropId,Integer areaLand) {
}
