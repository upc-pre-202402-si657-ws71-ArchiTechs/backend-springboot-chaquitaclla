package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.entities.Category;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources.CategoryResource;

public class CategoryResourceFromEntityAssembler {
    public static CategoryResource toResourceFromEntity(Category entity){
        return new CategoryResource(
                entity.getId(),
                entity.getName()
        );
    }
}
