package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Pest;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetAllPestsQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetPestByCropIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetPestByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PestQueryService {
    List<Pest> handle(GetAllPestsQuery query);
    Optional<Pest> findById(Long pestId);
    Optional<Pest> handle(GetPestByIdQuery getPestByIdQuery);
}
