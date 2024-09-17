package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.aggregates.Sowing;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.CreateSowingControlCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.DeleteSowingControlBySowingIdCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.UpdateSowingControlCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.entities.SowingControl;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services.SowingControlCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.infrastructure.persistence.jpa.repositories.SowingControlRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.infrastructure.persistence.jpa.repositories.SowingRepository;
import org.springframework.stereotype.Service;

@Service
public class SowingControlCommandServiceImpl implements SowingControlCommandService {

    private final SowingRepository sowingRepository;
    private final SowingControlRepository sowingControlsRepository;

    public SowingControlCommandServiceImpl(SowingRepository sowingRepository, SowingControlRepository sowingControlsRepository) {
        this.sowingRepository = sowingRepository;
        this.sowingControlsRepository = sowingControlsRepository;
    }

    @Override
    public void handle(UpdateSowingControlCommand command) {
        SowingControl sowingControl = sowingControlsRepository.findByIdAndSowingId(command.sowingControlId(), command.sowingId())
                .orElseThrow(() -> new IllegalArgumentException("Sowing Control with id " + command.sowingControlId() + " and Sowing id " + command.sowingId() + " does not exist"));

        sowingControl.setSowingCondition(command.sowingCondition());
        sowingControl.setSowingSoilMoisture(command.sowingSoilMoisture());
        sowingControl.setSowingStemCondition(command.sowingStemCondition());

        sowingControlsRepository.save(sowingControl);
    }
    @Override
    public Long handle(CreateSowingControlCommand command) {
        Sowing sowing = sowingRepository.findById(command.sowingId())
                .orElseThrow(() -> new IllegalArgumentException("Sowing with id " + command.sowingId() + " does not exist"));

        var sowingControl = new SowingControl(sowing, command.sowingCondition(), command.sowingSoilMoisture(), command.sowingStemCondition());
        sowingControlsRepository.save(sowingControl);
        return sowingControl.getId();
    }
    @Override
    public void handle(DeleteSowingControlBySowingIdCommand command) {
        if (!sowingControlsRepository.existsById(command.sowingControlId()))
            throw new IllegalArgumentException("Sowing Control does not exist");

        sowingControlsRepository.deleteById(command.sowingControlId());
    }
}