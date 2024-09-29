package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.UpdateQuestionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources.UpdateQuestionResource;

public class UpdateQuestionCommandFromResourceAssembler {
    public static UpdateQuestionCommand toCommandFromResource(Long questionId, UpdateQuestionResource resource){
        return new UpdateQuestionCommand(
                questionId,
                resource.categoryId(),
                resource.questionText()
        );
    }
}
