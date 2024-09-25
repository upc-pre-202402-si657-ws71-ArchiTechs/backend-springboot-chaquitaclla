package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.CityResource;

public class CityResourceFromEntityAssembler {
    public static CityResource toResourceFromEntity(City entity){
        return new CityResource(entity.getId(), entity.getName(), entity.getCountry().getId());
    }
}
