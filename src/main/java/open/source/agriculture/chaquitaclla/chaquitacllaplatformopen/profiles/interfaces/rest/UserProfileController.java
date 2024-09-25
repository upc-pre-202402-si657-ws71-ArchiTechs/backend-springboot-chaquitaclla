package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest;
import io.swagger.v3.oas.annotations.tags.Tag;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects.EmailUser;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects.PasswordUser;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.CreateUserProfileResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.UserProfileResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform.CreateUserProfileCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform.UserProfileResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@RestController
@RequestMapping(value="/api/v1/userProfile",produces = APPLICATION_JSON_VALUE)
@Tag(name = "UserProfile", description = "UserProfile Management Endpoints")
public class UserProfileController {
    private final UserProfileCommandService userProfileCommandService;
    private final UserProfileQueryService userProfileQueryService;

    public UserProfileController(UserProfileCommandService userProfileCommandService, UserProfileQueryService userProfileQueryService) {
        this.userProfileCommandService = userProfileCommandService;
        this.userProfileQueryService = userProfileQueryService;
    }
    @PostMapping
    public ResponseEntity<UserProfileResource> createUserProfile(@RequestBody CreateUserProfileResource createUserProfileResource) {
        var createUserProfileCommand = CreateUserProfileCommandFromResourceAssembler.toCommandFromResource(createUserProfileResource);
        var userProfile = userProfileCommandService.handle(createUserProfileCommand);
        if (userProfile.isEmpty())
            return ResponseEntity.badRequest().build();
        var userProfileResource = UserProfileResourceFromEntityAssembler.toResourceFromEntity(userProfile.get());
        return new ResponseEntity<>(userProfileResource, HttpStatus.CREATED);
    }
    @GetMapping("/{userProfileId}")
    public ResponseEntity<UserProfileResource> getUserProfileById(@PathVariable Long userProfileId) {
        var getUserProfileByIdQuery = new GetUserProfileByIdQuery(userProfileId);
        var userProfile = userProfileQueryService.handle(getUserProfileByIdQuery);
        if (userProfile.isEmpty())
            return ResponseEntity.notFound().build();
        var userProfileResource = UserProfileResourceFromEntityAssembler.toResourceFromEntity(userProfile.get());
        return new ResponseEntity<>(userProfileResource, HttpStatus.OK);
    }
    @GetMapping("/{userProfileEmail}/{userProfilePassword}")
    public ResponseEntity<UserProfileResource> getUserByEmailAndPassword(@PathVariable String userProfileEmail, @PathVariable String userProfilePassword) {
        EmailUser emailUser = new EmailUser(userProfileEmail);
        PasswordUser passwordUser = new PasswordUser(userProfilePassword);
        var getUserByEmailAndPasswordQuery = new GetUserProfileByEmailAndPasswordQuery(emailUser, passwordUser);
        var userProfile = userProfileQueryService.handle(getUserByEmailAndPasswordQuery);
        if (userProfile.isEmpty())
            return ResponseEntity.notFound().build();
        var userProfileResource = UserProfileResourceFromEntityAssembler.toResourceFromEntity(userProfile.get());
        return  ResponseEntity.ok(userProfileResource);
    }
    @GetMapping
    public ResponseEntity<List<UserProfileResource>> getAllUserProfiles() {
        var getAllUserProfilesQuery = new GetAllUserProfilesQuery();
        var userProfiles = userProfileQueryService.handle(getAllUserProfilesQuery);
        var userProfileResources = userProfiles.stream()
                .map(UserProfileResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userProfileResources);
    }
    @PutMapping("/{userProfileId}")
    public ResponseEntity<UserProfileResource> updateUserProfile(@PathVariable Long userProfileId, @RequestBody CreateUserProfileResource createUserProfileResource) {
        var updateUserProfileCommand = CreateUserProfileCommandFromResourceAssembler.toCommandFromResource(createUserProfileResource);
        var userProfile = userProfileCommandService.handle(updateUserProfileCommand);
        if (userProfile.isEmpty())
            return ResponseEntity.badRequest().build();
        var userProfileResource = UserProfileResourceFromEntityAssembler.toResourceFromEntity(userProfile.get());
        return new ResponseEntity<>(userProfileResource, HttpStatus.OK);
    }
    @DeleteMapping("/{userProfileId}")
    public ResponseEntity<?> deleteUserProfile(@PathVariable Long userProfileId) {
        var deleteUserProfileCommand = new DeleteUserProfileCommand(userProfileId);
        userProfileCommandService.handle(deleteUserProfileCommand);
        return ResponseEntity.ok("User with given id successfully deleted.");
    }
}
