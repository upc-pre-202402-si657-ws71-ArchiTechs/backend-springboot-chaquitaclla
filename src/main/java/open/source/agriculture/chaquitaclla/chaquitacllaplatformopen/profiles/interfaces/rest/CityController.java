package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.DeleteCityCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetAllCitysQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetCityByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.services.CityCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.services.CityQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.CityResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.CreateCityResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.UpdateCityResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform.CityResourceFromEntityAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform.CreateCityCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform.UpdateCityCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/api/v1/city",produces = APPLICATION_JSON_VALUE)
@Tag(name = "City", description = "City Management Endpoints")
public class CityController {
    private final CityCommandService cityCommandService;
    private final CityQueryService cityQueryService;

    public CityController(CityCommandService cityCommandService, CityQueryService cityQueryService) {
        this.cityCommandService = cityCommandService;
        this.cityQueryService = cityQueryService;
    }
    @PostMapping
    public ResponseEntity<CityResource> createCity(@RequestBody CreateCityResource createCityResource){
        var createCityCommand= CreateCityCommandFromResourceAssembler.toCommandFromResource(createCityResource);
        var cityId=cityCommandService.handle(createCityCommand);
        if (cityId ==0L){
            return ResponseEntity.badRequest().build();
        }
        var getCityByIdQuery= new GetCityByIdQuery(cityId);
        var city=cityQueryService.handle(getCityByIdQuery);
        if (city.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var cityResource= CityResourceFromEntityAssembler.toResourceFromEntity(city.get());
        return new ResponseEntity<>(cityResource, HttpStatus.CREATED);
    }

    // @GetMapping("/{cityId}")
    @GetMapping("/{cityId}")
    public ResponseEntity<CityResource> getCityById(@PathVariable Long cityId) {
        var getCityByIdQuery = new GetCityByIdQuery(cityId);
        var city = cityQueryService.handle(getCityByIdQuery);
        if (city.isEmpty())
            return ResponseEntity.badRequest().build();
        var cityResource = CityResourceFromEntityAssembler.toResourceFromEntity(city.get());
        return ResponseEntity.ok(cityResource);
    }

    @GetMapping
    public ResponseEntity<List<CityResource>> getAllCity() {
        var getAllCityQuery = new GetAllCitysQuery();
        var city = cityQueryService.handle(getAllCityQuery);
        var cityResources = city.stream().map(CityResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(cityResources);
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<CityResource> updateCity(@PathVariable Long cityId, @RequestBody UpdateCityResource updateCityResource) {
        var updateCityCommand = UpdateCityCommandFromResourceAssembler.toCommandFromResource(cityId, updateCityResource);
        var updatedCity = cityCommandService.handle(updateCityCommand);

        if (updatedCity.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var cityResource = CityResourceFromEntityAssembler.toResourceFromEntity(updatedCity.get());
        return ResponseEntity.ok(cityResource);
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<?> deleteCity(@PathVariable Long cityId) {
        var deleteCityCommand = new DeleteCityCommand(cityId);
        cityCommandService.handle(deleteCityCommand);
        return ResponseEntity.ok("City with given id successfully deleted");
    }


}
