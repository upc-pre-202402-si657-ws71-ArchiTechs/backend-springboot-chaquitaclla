package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.entities.SowingControl;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.queries.GetAllSowingControlsQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.queries.GetSowingControlByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.queries.GetSowingControlsBySowingIdQuery;

import java.util.List;
import java.util.Optional;

public interface SowingControlQueryService {
    List<SowingControl> handle(GetAllSowingControlsQuery query);
    List<SowingControl> handle(GetSowingControlsBySowingIdQuery query);
    Optional<SowingControl> handle(GetSowingControlByIdQuery query);

}
