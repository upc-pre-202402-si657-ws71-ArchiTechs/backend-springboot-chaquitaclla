package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.DeleteSubscriptionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetAllSubscriptionsQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetSubscriptionByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.services.SubscriptionCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.services.SubscriptionQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.CreateSubscriptionResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.SubscriptionResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.UpdateSubscriptionResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform.UpdateSubscriptionCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/profiles/subscription",produces = APPLICATION_JSON_VALUE)
@Tag(name="Subscription", description = "Subscription Management Endpoints")
public class SubscriptionController {
    private final SubscriptionCommandService subscriptionCommandService;
    private final SubscriptionQueryService subscriptionQueryService;

    public SubscriptionController(SubscriptionCommandService subscriptionCommandService, SubscriptionQueryService subscriptionQueryService) {
        this.subscriptionCommandService = subscriptionCommandService;
        this.subscriptionQueryService = subscriptionQueryService;
    }


    @PostMapping
    public ResponseEntity<SubscriptionResource> createSubscription(@RequestBody CreateSubscriptionResource createSubscriptionResource){
        var createSubscriptionCommand= CreateSubscriptionCommandFromResourceAssembler.toCommandFromResource(createSubscriptionResource);
        var subscriptionId=subscriptionCommandService.handle(createSubscriptionCommand);
        if (subscriptionId ==0L){
            return ResponseEntity.badRequest().build();
        }
        var getSubscriptionByIdQuery= new GetSubscriptionByIdQuery(subscriptionId);
        var subscription=subscriptionQueryService.handle(getSubscriptionByIdQuery);
        if (subscription.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var subscriptionResource= SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription.get());
        return new ResponseEntity<>(subscriptionResource, HttpStatus.CREATED);
    }

    @GetMapping("/{subscriptionId}")
    public ResponseEntity<SubscriptionResource> getSubscriptionById(@PathVariable Long subscriptionId) {
        var getSubscriptionByIdQuery = new GetSubscriptionByIdQuery(subscriptionId);
        var subscription = subscriptionQueryService.handle(getSubscriptionByIdQuery);
        if (subscription.isEmpty())
            return ResponseEntity.badRequest().build();
        var subscriptionResource = SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription.get());
        return ResponseEntity.ok(subscriptionResource);
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResource>> getAllSubscription() {
        var getAllSubscriptionQuery = new GetAllSubscriptionsQuery();
        var subscription = subscriptionQueryService.handle(getAllSubscriptionQuery);
        var subscriptionResources = subscription.stream().map(SubscriptionResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(subscriptionResources);
    }

    @PutMapping("/{subscriptionId}")
    public ResponseEntity<SubscriptionResource> updateSubscription(@PathVariable Long subscriptionId, @RequestBody UpdateSubscriptionResource updateSubscriptionResource) {
        var updateSubscriptionCommand = UpdateSubscriptionCommandFromResourceAssembler.toCommandFromResource(subscriptionId, updateSubscriptionResource);
        var updatedSubscription = subscriptionCommandService.handle(updateSubscriptionCommand);

        if (updatedSubscription.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var subscriptionResource = SubscriptionResourceFromEntityAssembler.toResourceFromEntity(updatedSubscription.get());
        return ResponseEntity.ok(subscriptionResource);
    }


    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<?> deleteSubscription(@PathVariable Long subscriptionId) {
        var deleteSubscriptionCommand = new DeleteSubscriptionCommand(subscriptionId);
        subscriptionCommandService.handle(deleteSubscriptionCommand);
        return ResponseEntity.ok("Subscription with given id successfully deleted");
    }

}
