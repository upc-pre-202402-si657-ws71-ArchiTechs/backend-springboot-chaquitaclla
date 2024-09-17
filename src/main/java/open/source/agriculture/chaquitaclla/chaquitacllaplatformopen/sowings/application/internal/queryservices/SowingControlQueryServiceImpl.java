package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.application.internal.queryservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.entities.SowingControl;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.queries.GetAllSowingControlsQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.queries.GetSowingControlByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.queries.GetSowingControlsBySowingIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services.SowingControlQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.infrastructure.persistence.jpa.repositories.SowingControlRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SowingControlQueryServiceImpl implements SowingControlQueryService {

    private final SowingControlRepository sowingControlRepository;

    public SowingControlQueryServiceImpl(SowingControlRepository sowingControlRepository) {
        this.sowingControlRepository = sowingControlRepository;
    }


    @Override
    public List<SowingControl> handle(GetAllSowingControlsQuery query) {
        return sowingControlRepository.findAll();
    }

    @Override
    public List<SowingControl> handle(GetSowingControlsBySowingIdQuery query) {
        return sowingControlRepository.findBySowingId(query.sowingId());
    }
    @Override
    public Optional<SowingControl> handle(GetSowingControlByIdQuery query) {
        return sowingControlRepository.findById(query.Id());
    }

}