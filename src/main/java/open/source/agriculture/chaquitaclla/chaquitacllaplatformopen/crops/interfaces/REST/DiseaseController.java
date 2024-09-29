package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST;

import io.swagger.v3.oas.annotations.tags.Tag;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Care;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Disease;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetAllDiseasesQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetDiseasesByCropIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.DiseaseCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.DiseaseQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CareResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CreateCareResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CreateDiseaseResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.DiseaseResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.CareResourceFromEntityAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.CreateDiseaseCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.DiseaseResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/crops/diseases")
@Tag(name = "Diseases", description = "Diseases Management Endpoints")
public class DiseaseController {

    private final DiseaseCommandService diseaseCommandService;
    private final DiseaseQueryService diseaseQueryService;
    private final CropQueryService cropQueryService;
    private final CropCommandService cropCommandService;

    public DiseaseController(DiseaseCommandService diseaseCommandService, DiseaseQueryService diseaseQueryService, CropQueryService cropQueryService, CropCommandService cropCommandService) {
        this.diseaseCommandService = diseaseCommandService;
        this.diseaseQueryService = diseaseQueryService;
        this.cropQueryService = cropQueryService;
        this.cropCommandService = cropCommandService;
    }


    @PostMapping
    public ResponseEntity<DiseaseResource> createDisease(@RequestBody CreateDiseaseResource diseaseResource) {
        Disease disease = new Disease(diseaseResource.name(), diseaseResource.description(), diseaseResource.solution());
        diseaseCommandService.save(disease);

        DiseaseResource diseaseResourceResponse = DiseaseResourceFromEntityAssembler.toResourceFromEntity(disease);
        return ResponseEntity.ok(diseaseResourceResponse);
    }
    @GetMapping
    public ResponseEntity<List<DiseaseResource>> getAllDiseases() {
        var getAllDiseasesQuery = new GetAllDiseasesQuery();
        var diseases = diseaseQueryService.handle(getAllDiseasesQuery);
        var diseaseResource = diseases.stream().map(DiseaseResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(diseaseResource);
    }


}