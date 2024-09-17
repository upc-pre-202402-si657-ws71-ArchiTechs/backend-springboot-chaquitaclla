package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.application.internal.outboundservices.acl.ExternalIamService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.aggregates.Subscription;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.aggregates.UserProfile;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.CreateUserProfileCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.DeleteUserProfileCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.commands.UpdateUserProfileCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.entities.City;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects.EmailUser;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects.PasswordUser;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects.PersonName;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.services.UserProfileCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.infrastructure.persistence.jpa.repositories.CityRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.infrastructure.persistence.jpa.repositories.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileCommandServiceImpl implements UserProfileCommandService {
    private final UserProfileRepository userProfileRepository;
    private final CityRepository cityRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final ExternalIamService externalIamService;

    public UserProfileCommandServiceImpl(UserProfileRepository userProfileRepository, CityRepository cityRepository, SubscriptionRepository subscriptionRepository, ExternalIamService externalIamService) {
        this.userProfileRepository = userProfileRepository;
        this.cityRepository = cityRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.externalIamService = externalIamService;
    }


    @Override
    public Optional<UserProfile> handle(CreateUserProfileCommand command) {
        City city=cityRepository.findById(command.cityId())
                .orElseThrow(()-> new IllegalArgumentException("City with id " + command.cityId() + " not found"));
        Subscription subscription = subscriptionRepository.findById(command.subscriptionId())
                .orElseThrow(() -> new IllegalArgumentException("Subscription with id " + command.subscriptionId() + " not found"));
        var emailUser= new EmailUser(command.email());
        userProfileRepository.findByEmail(emailUser).map(userProfile -> {
            throw new IllegalArgumentException("User with email " + command.email() + " already exists");
        });
        var userId= externalIamService.fetchUserIdByUsername(command.email());
        if(userId==0){
            List<String> roleNames = new ArrayList<>();
            roleNames.add("ROLE_USER");
            userId= externalIamService.createUser(command.email(), command.password(), roleNames);
            if(userId == 0){
                throw new IllegalArgumentException("Error creating user in IAM context");
            }
        }
        var  userProfile = new UserProfile(command.firstName(),command.lastName(), command.email(), command.password(), city, subscription);
        userProfileRepository.save(userProfile);
        return Optional.of(userProfile);
    }

    @Override
    public Optional<UserProfile> handle(UpdateUserProfileCommand command) {
        var result = userProfileRepository.findById(command.id());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("User with id " + command.id() + " not found");
        }
        var userProfileToUpdate = result.get();
        City city = cityRepository.findById(command.cityId()).orElseThrow(() -> new IllegalArgumentException("City with id " + command.cityId() + " not found"));
        Subscription subscription = subscriptionRepository.findById(command.subscriptionId())
                .orElseThrow(() -> new IllegalArgumentException("Subscription with id " + command.subscriptionId() + " not found"));
        var emailUser = new EmailUser(command.email());
        userProfileRepository.findByEmail(emailUser).map(userProfile -> {
            throw new IllegalArgumentException("User with email " + command.email() + " already exists");
        });
        try{
            PersonName personName = new PersonName(command.firstName(), command.lastName());
            PasswordUser passwordUser= new PasswordUser(command.password());
            userProfileToUpdate.setName(personName);
            userProfileToUpdate.setEmail(emailUser);
            userProfileToUpdate.setPassword(passwordUser);
            userProfileToUpdate.setCity(city);
            userProfileToUpdate.setSubscription(subscription);
            userProfileRepository.save(userProfileToUpdate);
            return Optional.of(userProfileToUpdate);
        }
        catch(Exception e){
            throw new IllegalArgumentException("Error updating user profile: "+e.getMessage());
        }
    }

    @Override
    public void handle(DeleteUserProfileCommand command) {
        if (!userProfileRepository.existsById(command.userProfileId())) {
            throw new IllegalArgumentException("User does not exist");
        }
        try {
            userProfileRepository.deleteById(command.userProfileId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting user: " + e.getMessage());
        }
    }
}
