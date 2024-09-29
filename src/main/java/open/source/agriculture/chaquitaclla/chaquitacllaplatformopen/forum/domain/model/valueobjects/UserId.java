package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserId(Long userId) {

    public UserId(){
        this(0L);
    }


    public UserId{
        if(userId  < 0){
            throw new IllegalArgumentException("User userId cannot be negative");
        }

    }
}
