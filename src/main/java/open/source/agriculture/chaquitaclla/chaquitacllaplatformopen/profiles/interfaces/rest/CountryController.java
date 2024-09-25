package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest;
import io.swagger.v3.oas.annotations.tags.Tag;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.CountryResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.CreateCountryResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.UpdateCountryResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform.CountryResourceFromEntityAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform.CreateCountryCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform.UpdateCountryCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/api/v1/country",produces = APPLICATION_JSON_VALUE)
@Tag(name = "Country", description = "Country Management Endpoints")
public class CountryController {
    private final CountryCommandService countryCommandService;
    private final CountryQueryService countryQueryService;

    public CountryController(CountryCommandService countryCommandService, CountryQueryService countryQueryService) {
        this.countryCommandService = countryCommandService;
        this.countryQueryService = countryQueryService;
    }
    @PostMapping
    public ResponseEntity<CountryResource> createCountry(@RequestBody CreateCountryResource createCountryResource){
        var createCountryCommand= CreateCountryCommandFromResourceAssembler.toCommandFromResource(createCountryResource);
        var countryId=countryCommandService.handle(createCountryCommand);
        if (countryId ==0L){
            return ResponseEntity.badRequest().build();
        }
        var getCountryByIdQuery= new GetCountryByIdQuery(countryId);
        var country=countryQueryService.handle(getCountryByIdQuery);
        if (country.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var countryResource= CountryResourceFromEntityAssembler.toResourceFromEntity(country.get());
        return new ResponseEntity<>(countryResource, HttpStatus.CREATED);
    }
    @GetMapping("/{countryId}")
    public ResponseEntity<CountryResource> getCountryById(@PathVariable Long countryId) {
        var getCountryByIdQuery = new GetCountryByIdQuery(countryId);
        var country = countryQueryService.handle(getCountryByIdQuery);
        if (country.isEmpty())
            return ResponseEntity.badRequest().build();
        var countryResource = CountryResourceFromEntityAssembler.toResourceFromEntity(country.get());
        return ResponseEntity.ok(countryResource);
    }

    @GetMapping
    public ResponseEntity<List<CountryResource>> getAllCountry() {
        var getAllCountryQuery = new GetAllCountrysQuery();
        var country = countryQueryService.handle(getAllCountryQuery);
        var countryResources = country.stream().map(CountryResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(countryResources);
    }

    @PutMapping("/{countryId}")
    public ResponseEntity<CountryResource> updateCountry(@PathVariable Long countryId, @RequestBody UpdateCountryResource updateCountryResource) {
        var updateCountryCommand = UpdateCountryCommandFromResourceAssembler.toCommandFromResource(countryId, updateCountryResource);
        var updatedCountry = countryCommandService.handle(updateCountryCommand);

        if (updatedCountry.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var countryResource = CountryResourceFromEntityAssembler.toResourceFromEntity(updatedCountry.get());
        return ResponseEntity.ok(countryResource);
    }


    @DeleteMapping("/{countryId}")
    public ResponseEntity<?> deleteCountry(@PathVariable Long countryId) {
        var deleteCountryCommand = new DeleteCountryCommand(countryId);
        countryCommandService.handle(deleteCountryCommand);
        return ResponseEntity.ok("Country with given id successfully deleted");
    }


}
