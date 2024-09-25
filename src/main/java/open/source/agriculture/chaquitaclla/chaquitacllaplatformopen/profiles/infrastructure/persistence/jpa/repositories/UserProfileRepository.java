package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.infrastructure.persistence.jpa.repositories;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects.EmailUser;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects.PasswordUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findById(Long id);
    Optional<UserProfile> findByEmail(EmailUser emailUser);
    Optional<UserProfile> findByEmailAndPassword(EmailUser emailUser, PasswordUser passwordUser);
}
