package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.aggregates;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.entities.City;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects.EmailUser;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects.PasswordUser;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects.PersonName;
import org.springframework.data.domain.AbstractAggregateRoot;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "\"user_profile\"")
public class UserProfile extends AbstractAggregateRoot<UserProfile> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PersonName name;
    @Embedded
    @AttributeOverride(name="email",column = @Column(name = "email"))
    private EmailUser email;

    @Embedded
    @AttributeOverride(name="password",column = @Column(name = "password"))
    private PasswordUser password;

    @ManyToOne
    @JoinColumn(name = "city_id",nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "subscription_id",nullable = false)
    private Subscription subscription;
    public UserProfile() {
    }
    public UserProfile(String firstName, String lastName, String email, String password, City city, Subscription subscription) {
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailUser(email);
        this.password = new PasswordUser(password);
        this.city = city;
        this.subscription = subscription;
    }
}
