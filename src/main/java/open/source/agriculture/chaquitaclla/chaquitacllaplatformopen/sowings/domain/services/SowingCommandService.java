package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.aggregates.Sowing;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.CreateSowingCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.DeleteSowingCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.UpdateSowingCommand;

import java.util.Optional;

public interface SowingCommandService {
    Long handle(CreateSowingCommand command);
    Optional<Sowing> handle(UpdateSowingCommand command);
    void handle(DeleteSowingCommand command);

}
