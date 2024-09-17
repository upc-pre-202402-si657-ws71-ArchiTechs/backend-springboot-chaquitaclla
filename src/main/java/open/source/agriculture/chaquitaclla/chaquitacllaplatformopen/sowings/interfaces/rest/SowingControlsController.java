package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.DeleteSowingControlBySowingIdCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.queries.GetAllSowingControlsQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.queries.GetSowingByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.queries.GetSowingControlByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.queries.GetSowingControlsBySowingIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services.SowingControlCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services.SowingControlQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services.SowingQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.CreateSowingControlResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.SowingControlResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.UpdateSowingControlResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.transform.CreateSowingControlCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.transform.SowingControlResourceFromEntityAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.transform.UpdateSowingControlCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/sowings", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sowings", description = "Sowing Management Endpoints")
public class SowingControlsController {
    private final SowingControlCommandService sowingControlCommandService;
    private final SowingControlQueryService sowingControlQueryService;
    private final SowingQueryService sowingQueryService;

    public SowingControlsController(SowingControlCommandService sowingControlCommandService,
                                    SowingControlQueryService sowingControlQueryService,
                                    SowingQueryService sowingQueryService) {
        this.sowingControlCommandService = sowingControlCommandService;
        this.sowingControlQueryService = sowingControlQueryService;
        this.sowingQueryService = sowingQueryService;
    }

   @PostMapping("/{sowingId}/controls")
public ResponseEntity<SowingControlResource> createSowingControl(@PathVariable Long sowingId, @RequestBody CreateSowingControlResource sowingControlResource) {

        var createSowingControlCommand = CreateSowingControlCommandFromResourceAssembler.fromResource(sowingId, sowingControlResource);
        var sowingControlId = sowingControlCommandService.handle(createSowingControlCommand);

    if(sowingControlId == 0L)
        return ResponseEntity.badRequest().build();

    var getSowingControlByIdQuery = new GetSowingControlByIdQuery(sowingControlId);
    var sowingControlOptional = sowingControlQueryService.handle(getSowingControlByIdQuery);

    if(sowingControlOptional.isEmpty())
        return ResponseEntity.badRequest().build();

    var sowingControlEntity = sowingControlOptional.get();
    var sowingControlResourceResponse = SowingControlResourceFromEntityAssembler.toResourceFromEntity(sowingControlEntity);

    return new ResponseEntity<>(sowingControlResourceResponse, HttpStatus.CREATED);
}
    @GetMapping("/{sowingId}/controls")
    public ResponseEntity<List<SowingControlResource>> getAllSowingControlsBySowingId(@PathVariable Long sowingId) {
        var getAllSowingControlsBySowingIdQuery = new GetSowingControlsBySowingIdQuery(sowingId);
        var sowingControls = sowingControlQueryService.handle(getAllSowingControlsBySowingIdQuery);
        var sowingControlResources = sowingControls.stream()
                .map(SowingControlResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sowingControlResources);
    }
    @DeleteMapping("/{sowingId}/controls/{sowingControlId}")
    public ResponseEntity<?> deleteSowingControl(@PathVariable Long sowingId, @PathVariable Long sowingControlId) {
        // Verificar si existe un Sowing con el sowingId proporcionado
        var getSowingByIdQuery = new GetSowingByIdQuery(sowingId);
        var sowingOptional = sowingQueryService.handle(getSowingByIdQuery);

        if (sowingOptional.isEmpty()) {
            // Si no existe, devolver un error
            return ResponseEntity.badRequest().body("No se encontró un Sowing con el ID proporcionado: " + sowingId);
        }

        // Si el Sowing existe, proceder a eliminar el control
        var deleteSowingControlCommand = new DeleteSowingControlBySowingIdCommand(sowingId, sowingControlId);
        sowingControlCommandService.handle(deleteSowingControlCommand);
        return ResponseEntity.ok("SowingControl with given id successfully deleted");
    }

    @GetMapping("/controls")
    public ResponseEntity<List<SowingControlResource>> getAllSowingControls() {
        var getAllSowingControlsQuery = new GetAllSowingControlsQuery();
        var sowingControls = sowingControlQueryService.handle(getAllSowingControlsQuery);
        var sowingControlResources = sowingControls.stream()
                .map(SowingControlResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sowingControlResources);
    }
    @PutMapping("/{sowingId}/controls/{sowingControlId}")
    public ResponseEntity<SowingControlResource> updateSowingControl(@PathVariable Long sowingId, @PathVariable Long sowingControlId, @RequestBody UpdateSowingControlResource updateSowingControlResource) {
        var updateSowingControlCommand = UpdateSowingControlCommandFromResourceAssembler.fromResource(updateSowingControlResource,sowingId, sowingControlId);
        sowingControlCommandService.handle(updateSowingControlCommand);

        var getSowingControlByIdQuery = new GetSowingControlByIdQuery(sowingControlId);
        var sowingControlOptional = sowingControlQueryService.handle(getSowingControlByIdQuery);

        if(sowingControlOptional.isEmpty())
            return ResponseEntity.badRequest().build();

        var sowingControlEntity = sowingControlOptional.get();
        var sowingControlResourceResponse = SowingControlResourceFromEntityAssembler.toResourceFromEntity(sowingControlEntity);

        return ResponseEntity.ok(sowingControlResourceResponse);
    }
    /*@GetMapping
    public ResponseEntity<List<Object>> getAllSowingControls() {
        var getAllSowingControlsQuery = new GetAllSowingControlsQuery();
        var sowingControls = sowingControlQueryService.handle(getAllSowingControlsQuery);
        var sowingControlResources = sowingControls.stream()
                .map(SowingControlResource::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sowingControlResources);
    }*/
    //@GetMapping("/{sowingId}")
    //public ResponseEntity<SowingControlResource> getSowingControlBySowingId(@PathVariable Long sowingId) {}
}
