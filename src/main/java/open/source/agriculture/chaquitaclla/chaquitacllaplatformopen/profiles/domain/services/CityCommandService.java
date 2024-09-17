package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.CreateCityCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.DeleteCityCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.UpdateCityCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.entities.City;

import java.util.Optional;

public interface CityCommandService {
    Long handle(CreateCityCommand command);
    Optional<City> handle(UpdateCityCommand command);
    void handle(DeleteCityCommand command);

}
