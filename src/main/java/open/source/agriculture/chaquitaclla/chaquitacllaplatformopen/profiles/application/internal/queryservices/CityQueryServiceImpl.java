package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.application.internal.queryservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.entities.City;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetAllCitysQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetCityByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.services.CityQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.infrastructure.persistence.jpa.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityQueryServiceImpl implements CityQueryService {
    private final CityRepository cityRepository;

    public CityQueryServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Optional<City> handle(GetCityByIdQuery query) {
        return cityRepository.findById(query.cityId());
    }

    @Override
    public List<City> handle(GetAllCitysQuery query) {
        return cityRepository.findAll();
    }
}
