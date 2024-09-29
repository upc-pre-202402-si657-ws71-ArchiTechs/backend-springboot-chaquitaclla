package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.infrastructure.hashing.bcrypt;

import org.springframework.security.crypto.password.PasswordEncoder;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.application.internal.outboundservices.hashing.HashingService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.infrastructure.hashing.bcrypt.services.HashingServiceImpl;

/**
 * This interface is a marker interface for the BCrypt hashing service.
 * It extends the {@link HashingService} and {@link PasswordEncoder} interfaces.
 * This interface is used to inject the BCrypt hashing service in the {@link HashingServiceImpl} class.
 */
public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
