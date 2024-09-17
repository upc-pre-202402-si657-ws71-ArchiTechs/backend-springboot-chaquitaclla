package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.entities.Country;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetAllCountrysQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetCountryByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CountryQueryService {
    Optional<Country> handle(GetCountryByIdQuery query);
    List<Country> handle(GetAllCountrysQuery query);
}
