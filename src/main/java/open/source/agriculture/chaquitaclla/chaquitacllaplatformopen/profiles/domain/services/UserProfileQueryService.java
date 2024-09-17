package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.aggregates.UserProfile;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetAllUserProfilesQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetUserProfileByEmailAndPasswordQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetUserProfileByEmailQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.queries.GetUserProfileByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserProfileQueryService {
    Optional<UserProfile> handle(GetUserProfileByIdQuery query);
    Optional<UserProfile> handle(GetUserProfileByEmailQuery query);
    Optional<UserProfile> handle(GetUserProfileByEmailAndPasswordQuery query);
    List<UserProfile> handle(GetAllUserProfilesQuery query);
}
