package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.interfaces.rest.resources.CountryResource;

public class CountryResourceFromEntityAssembler {
    public static CountryResource toResourceFromEntity(Country entity){
        return  new CountryResource(entity.getId(), entity.getName());
    }
}
