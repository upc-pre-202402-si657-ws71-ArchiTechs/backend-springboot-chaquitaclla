package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.aggregates.Sowing;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.queries.GetAllSowingsQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.queries.GetSowingByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.queries.GetSowingsByPhenologicalPhaseQuery;

import java.util.List;
import java.util.Optional;

public interface SowingQueryService {
    List<Sowing> handle(GetAllSowingsQuery query);
    Optional<Sowing> handle(GetSowingByIdQuery query);
    List<Sowing> handle(GetSowingsByPhenologicalPhaseQuery query);
}
