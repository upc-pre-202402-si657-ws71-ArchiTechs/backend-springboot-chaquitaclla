package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.CreateSowingControlCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.DeleteSowingControlBySowingIdCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.UpdateSowingControlCommand;

public interface SowingControlCommandService {
    Long handle(CreateSowingControlCommand command);
    void handle(DeleteSowingControlBySowingIdCommand command);
    void handle(UpdateSowingControlCommand command);
}