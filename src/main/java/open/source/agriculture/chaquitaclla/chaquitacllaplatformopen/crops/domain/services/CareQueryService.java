package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Care;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetAllCaresQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetCaresByCropIdQuery;

import java.util.List;
import java.util.Optional;

public interface CareQueryService {
    List<Care> handle(GetAllCaresQuery query);

    Optional<Care> handle(GetCaresByCropIdQuery query);

    Optional<Care> findById(Long careId);


}
