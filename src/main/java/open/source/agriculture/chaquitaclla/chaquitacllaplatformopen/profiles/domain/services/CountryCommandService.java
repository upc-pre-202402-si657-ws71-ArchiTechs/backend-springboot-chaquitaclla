package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.CreateCountryCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.DeleteCountryCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.UpdateCountryCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.entities.Country;

import java.util.Optional;

public interface CountryCommandService {
    Long handle(CreateCountryCommand command);
    Optional<Country> handle(UpdateCountryCommand command);
    void handle(DeleteCountryCommand command);
}
