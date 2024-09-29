package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.application.internal.outboundservices.acl;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.iam.interfaces.acl.IamContextFacade;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalIamService {
    private final IamContextFacade iamContextFacade;

    public ExternalIamService(@Lazy IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }
    public Long fetchUserIdByUsername(String username){
        var userId= iamContextFacade.fetchUserIdByUsername(username);
        return userId;
    }
    public Long createUser(String username, String password, List<String> roleNames) {
        var userId = iamContextFacade.createUser(username, password, roleNames);
        return userId;
    }

}
