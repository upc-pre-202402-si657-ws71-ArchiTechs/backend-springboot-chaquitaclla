package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.entities.City;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetAllCitysQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetCityByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CityQueryService {
    Optional<City> handle(GetCityByIdQuery query);
    List<City> handle(GetAllCitysQuery query);
}
