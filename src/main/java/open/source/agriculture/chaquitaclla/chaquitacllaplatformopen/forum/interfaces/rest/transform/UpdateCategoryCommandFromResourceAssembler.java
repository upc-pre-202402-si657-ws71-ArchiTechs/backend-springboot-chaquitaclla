package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.UpdateCategoryCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources.UpdateCategoryResource;

public class UpdateCategoryCommandFromResourceAssembler {
    public static UpdateCategoryCommand toCommandFromResource(Long categoryId, UpdateCategoryResource resource){
        return new UpdateCategoryCommand(
                categoryId,
                resource.name()
        );
    }
}
