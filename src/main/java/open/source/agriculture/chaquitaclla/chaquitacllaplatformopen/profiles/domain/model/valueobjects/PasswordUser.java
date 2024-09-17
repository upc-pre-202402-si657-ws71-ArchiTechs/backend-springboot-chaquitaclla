package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PasswordUser(String passwordUsers) {
    public PasswordUser() {
        this(null);
    }
    public PasswordUser{
        if (passwordUsers == null || passwordUsers.isBlank()|| passwordUsers.length() < 8) {
            throw new IllegalArgumentException("Invalid password, it must be at least 8 characters long");
        }
    }
}
