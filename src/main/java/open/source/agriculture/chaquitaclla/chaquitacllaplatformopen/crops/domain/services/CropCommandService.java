package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateCropCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.DeleteCropCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.UpdateCropCommand;

import java.util.Optional;

public interface CropCommandService {
    Long handle(CreateCropCommand command);
    Optional<Crop> handle(UpdateCropCommand command);
    void handle(DeleteCropCommand command);

    void save(Crop crop);
}