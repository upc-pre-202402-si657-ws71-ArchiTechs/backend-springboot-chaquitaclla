package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.application.internal.queryservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.aggregates.UserProfile;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetAllUserProfilesQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetUserProfileByEmailAndPasswordQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetUserProfileByEmailQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetUserProfileByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.services.UserProfileQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.infrastructure.persistence.jpa.repositories.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserProfileQueryServiceImpl implements UserProfileQueryService {
    private final UserProfileRepository userProfileRepository;

    public UserProfileQueryServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public Optional<UserProfile> handle(GetUserProfileByIdQuery query) {
        return userProfileRepository.findById(query.userProfileId());
    }

    @Override
    public Optional<UserProfile> handle(GetUserProfileByEmailQuery query) {
        return userProfileRepository.findByEmail(query.emailUser());
    }

    @Override
    public Optional<UserProfile> handle(GetUserProfileByEmailAndPasswordQuery query) {
        return userProfileRepository.findByEmailAndPassword(query.emailUser(), query.passwordUser());
    }

    @Override
    public List<UserProfile> handle(GetAllUserProfilesQuery query) {
        return userProfileRepository.findAll();
    }
}
