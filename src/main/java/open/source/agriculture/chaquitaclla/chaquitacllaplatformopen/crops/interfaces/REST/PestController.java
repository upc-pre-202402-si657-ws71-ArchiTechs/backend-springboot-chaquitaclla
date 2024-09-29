package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST;

import io.swagger.v3.oas.annotations.tags.Tag;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Care;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Pest;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetAllPestsQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.PestCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.PestQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.PestRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CareResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CreateCareResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CreatePestResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.PestResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.CareResourceFromEntityAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.CreatePestCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.PestResourceFromEntityAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/crops/pests")
@Tag(name = "Pests", description = "Pests Management Endpoints")
public class PestController {

    private final PestCommandService pestCommandService;
    private final PestQueryService pestQueryService;
    private final CropQueryService cropQueryService;
    private final CropCommandService cropCommandService;

    @Autowired
    private PestRepository pestRepository;

    public PestController(PestCommandService pestCommandService, PestQueryService pestQueryService, CropQueryService cropQueryService, CropCommandService cropCommandService) {
        this.pestCommandService = pestCommandService;
        this.pestQueryService = pestQueryService;
        this.cropQueryService = cropQueryService;
        this.cropCommandService = cropCommandService;
    }


    @PostMapping
    public ResponseEntity<PestResource> createPest(@RequestBody CreatePestResource pestResource) {
        Pest pest = new Pest(pestResource.name(),pestResource.description(),pestResource.solution());
        pestCommandService.save(pest);

        PestResource pestResourceResponse = PestResourceFromEntityAssembler.toResourceFromEntity(pest);
        return ResponseEntity.ok(pestResourceResponse);
    }
    @GetMapping
    public ResponseEntity<List<PestResource>> getAllPests() {
        var getAllPestsQuery = new GetAllPestsQuery();
        var pests = pestQueryService.handle(getAllPestsQuery);
        var pestResource = pests.stream().map(PestResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(pestResource);
    }


}