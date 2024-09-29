package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects;
import jakarta.persistence.Embeddable;

@Embeddable
public record NameSubsCription(String subscriptionName) {
    public NameSubsCription(){
        this(null);
    }
    public NameSubsCription {
        if (subscriptionName == null) {
            throw new IllegalArgumentException("Subscription cannot be null");
        }
    }
}
