package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.CreateCategoryCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources.CreateCategoryResource;

public class CreateCategoryCommandFromResourceAssembler {
    public static CreateCategoryCommand toCommandFromResource(CreateCategoryResource command){
        return new CreateCategoryCommand(
                command.name()
        );
    }
}
