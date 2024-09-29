package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.CreateCategoryCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.DeleteCategoryCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.UpdateCategoryCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.entities.Category;

import java.util.Optional;

public interface CategoryCommandService {
    Long handle(CreateCategoryCommand command);
    Optional<Category> handle(UpdateCategoryCommand command);
    void handle(DeleteCategoryCommand command);
}
