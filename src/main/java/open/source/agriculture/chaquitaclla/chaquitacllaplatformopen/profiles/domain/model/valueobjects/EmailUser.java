package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;

@Embeddable
public record EmailUser(@Email String address) {
  public EmailUser() {
    this(null);
  }
  public EmailUser {
    if (address == null) {
      throw new IllegalArgumentException("Email address cannot be null");
    }
  }
}
